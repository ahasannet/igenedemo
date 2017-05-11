<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html ng-app="igeneApp">
<head>
<title>Igene AngularJS Demo</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="<c:url value='/web/css/app.css' />" rel="stylesheet">

<style>
table, th, td {
	border: 1px solid grey;
	border-collapse: collapse;
	padding: 5px;
}

table tr:nth-child(odd) {
	background-color: #f1f1f1;
}

table tr:nth-child(even) {
	background-color: #ffffff;
}
</style>

</head>
<body ng-controller="IgeneController as controller" class="ng-cloak">
	<div class="generic-container">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="lead">Droplets</span>
			</div>
			<ul class="nav nav-pills">
				<li class="active"><a data-toggle="pill" href="#droplets">Droplets</a></li>
				<li><a data-toggle="pill" href="#volumes">Volumes</a></li>
			</ul>
			<div class="tab-content">
				<div id="droplets" class="row tab-pane fade in active">
					<table style="margin-left: 15px; width: 95%;">
						<tr>
							<th>Image</th>
							<th>Version</th>
							<th>1 Click Applications</th>
							<th>Type</th>
							<th>Region</th>
							<th>Slot</th>
							<th>Additional Options</th>
							<th>SSH Key</th>
							<th>Number of Droplet</th>
							<th>Hostname</th>
							<th>Created</th>
							<th>Edit</th>
						</tr>
						<tr ng-repeat="droplet in dropletList">
							<td>{{droplet.distribution}}</td>
							<td>{{droplet.version}}</td>
							<td>{{droplet.applications}}</td>
							<td>{{droplet.serverType}}</td>
							<td>{{droplet.dataCenter}}</td>
							<td>{{droplet.slot}}</td>
							<td>{{droplet.additionalOptions}}</td>
							<td>{{droplet.sshKeys}}</td>
							<td>{{droplet.numberOfDroplets}}</td>
							<td>{{droplet.hostname}}</td>
							<td>{{droplet.createdOn}}</td>
							<td><button ng-click="onEditDroplet(droplet.id)"><span class="glyphicon glyphicon-pencil"></span></button></td>
						</tr>
					</table>
				</div>
				<div id="volumes" class="row tab-pane fade">
					<table style="margin-left: 15px; width: 95%;">
						<tr>
							<th>Monthly Rate</th>
							<th>Hourly Rate</th>
							<th>Ram</th>
							<th>CPU</th>
							<th>Disk Size</th>
							<th>Bandwidth</th>
							<th>Usage</th>
						</tr>
						<tr ng-repeat="droplet in dropletList">
							<td>{{droplet.configuration.currency}}{{droplet.configuration.monthlyRate}}</td>
							<td>{{droplet.configuration.currency}}{{droplet.configuration.hourlyRate}}</td>
							<td>{{droplet.configuration.ram}}</td>
							<td>{{droplet.configuration.cpu}}</td>
							<td>{{droplet.configuration.diskSize}}</td>
							<td>{{droplet.configuration.bandwidth}}</td>
							<td>{{droplet.configuration.currency}}{{droplet.usage}}</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="panel-heading">
				<span class="lead">{{$scope.droplet.id > 0 ? "Update" : "Create"}} Droplets</span>
			</div>
			<div class="formcontainer">
				<form name="myForm" class="form-horizontal">

					<div class="panel-heading">
						<span class="lead">Choose an image <span
							class="glyphicon glyphicon-question-sign" title="Image Help"></span></span>
					</div>

					<ul class="nav nav-pills">
						<li class="active"><a data-toggle="pill"
							href="#distributions">Distributions</a></li>
						<li><a data-toggle="pill" href="#oneclickapps">One-click
								apps</a></li>
					</ul>

					<div class="tab-content">
						<div id="distributions" class="row tab-pane fade in active">
							<br />
							<div class="col-md-2 media"
								ng-repeat="distribution in referenceData.distributionList"
								style="cursor: pointer;"
								ng-click="onDistributionClick(distribution.id)">
								<div id="distribution_div_{{distribution.id}}"
									style="width: 104px; border-style: solid; border-color: #cccccc; border-width: 2px;">
									<img src="{{distribution.icon}}" class="media-object"
										style="width: 100px;" />
									<p class="text-center" style="margin-top: 10px;">{{distribution.name}}</p>
									<hr id="distribution_hr_{{distribution.id}}"
										style="border-top: 2px solid #cccccc;" />
									<span class="form-group"> <select class="form-control"
										style="width: 100px;" ng-model="droplet.version">
											<option ng-repeat="version in distribution.versions"
												value="{{version}}">{{version}}</option>
									</select>
									</span>
								</div>
							</div>
						</div>
						<div id="oneclickapps" class="row tab-pane fade">
							<br />
							<div class="col-md-2 media"
								ng-repeat="oneClickApplication in referenceData.oneClickApplicationList | orderBy:'name'"
								style="cursor: pointer;"
								ng-click="onAppplicationClick(oneClickApplication.id)">
								<div id="application_div_{{oneClickApplication.id}}"
									style="width: 104px; border-style: solid; border-color: #cccccc; border-width: 2px;">
									<img src="{{oneClickApplication.icon}}" class="media-object"
										style="width: 100px;">
									<hr id="application_hr_{{oneClickApplication.id}}"
										style="border-top: 2px solid #cccccc;" />
									<span class="form-group">
										<p class="text-center" style="margin-top: 10px;">{{oneClickApplication.name}}</p>
									</span>
								</div>
							</div>
						</div>
					</div>

					<div class="panel-heading">
						<span class="lead">Choose a size</span>
					</div>

					<ul class="nav nav-pills">
						<li class="active"><a data-toggle="pill" href="#standard">Standard</a></li>
						<li><a data-toggle="pill" href="#highmemory">High Memory</a></li>
					</ul>

					<div class="tab-content">
						<div id="standard" class="tab-pane fade in active">
							<br />
							<div class="col-md-2 media"
								ng-repeat="configuration in referenceData.serverConfigurationList | orderBy:'monthlyRate'"
								style="cursor: pointer;"
								ng-click="onServerConfigurationClick(configuration.id)">
								<div id="standard_server_div_{{configuration.id}}"
									style="width: 150px; border-style: solid; border-color: #cccccc;">
									<span class="form-group">
										<p class="text-center">{{configuration.currency}}{{configuration.monthlyRate}}/mo</p>
										<p class="text-center">{{configuration.currency}}{{configuration.hourlyRate}}/hour</p>
									</span>
									<hr id="standard_server_hr_{{configuration.id}}"
										style="border-top: 2px solid #cccccc;" />
									<span class="form-group">
										<p class="text-center">{{configuration.ram}}/{{configuration.cpu}}
											CPU</p>
										<p class="text-center">{{configuration.diskSize}} SSD disk</p>
										<p class="text-center">{{configuration.bandwidth}}
											transfer</p>
									</span>
								</div>
							</div>
						</div>
						<div id="highmemory" class="tab-pane fade">
							<br />
							<div class="col-md-2 media"
								ng-repeat="configuration in referenceData.highMemoryServerList | orderBy:'monthlyRate'"
								style="cursor: pointer;"
								ng-click="onHighMemoryServerClick(configuration.id)">
								<div id="high_memory_server_div_{{configuration.id}}"
									style="width: 150px; border-style: solid; border-color: #cccccc;">
									<span class="form-group">
										<p class="text-center">{{configuration.currency}}{{configuration.monthlyRate}}/mo</p>
										<p class="text-center">{{configuration.currency}}{{configuration.hourlyRate}}/hour</p>
									</span>
									<hr id="high_memory_server_hr_{{configuration.id}}"
										style="border-top: 2px solid #cccccc;" />
									<span class="form-group">
										<p class="text-center">{{configuration.ram}}/{{configuration.cpu}}
											CPU</p>
										<p class="text-center">{{configuration.diskSize}} SSD disk</p>
										<p class="text-center">{{configuration.bandwidth}}
											transfer</p>
									</span>
								</div>
							</div>
						</div>
					</div>
					<div class="row panel-heading">
						<span class="row lead"></span>
					</div>
					<div class="row panel-heading">
						<span class="row lead">Choose a data center location</span>
					</div>
					<div class="row tab-pane fade in">
						<br />
						<div class="col-md-2 media"
							ng-repeat="dataCenterRegion in referenceData.dataCenterRegionList | orderBy:'name'"
							style="cursor: pointer;">
							<div id="data_center_div_{{dataCenterRegion.id}}"
								style="width: 104px; border-style: solid; border-color: #cccccc; border-width: 2px;">
								<img src="{{dataCenterRegion.icon}}" class="media-object"
									style="width: 100px;" />
								<p class="text-center" style="margin-top: 10px;">{{dataCenterRegion.name}}</p>
								<hr id="data_center_hr_{{dataCenterRegion.id}}"
									style="border-top: 2px solid #cccccc;" />
								<div class="btn-group">
									<button
										id="data_center_button_{{dataCenterRegion.id}}_{{slot}}"
										type="button" class="btn btn-primary"
										ng-repeat="slot in dataCenterRegion.slots"
										style="color: #6b6b70; background: #cccccc;{{dataCenterRegion.slots.length == 1 ? 'width:101px;' : ''}}{{dataCenterRegion.slots.length == 2 ? 'width:50px;' : ''}}"
										ng-click="onDataCenterButtonClick(dataCenterRegion.id, slot)">{{slot}}</button>
								</div>
							</div>
						</div>
					</div>
					<div class="panel-heading">
						<span class="lead">Select additional options <span
							class="glyphicon glyphicon-question-sign"
							title="Additional Opion Help"></span></span>
					</div>
					<div class="row tab-pane fade in">
						<br />
						<div class="col-md-2 media"
							ng-repeat="additionalOption in referenceData.additionalOptionList | orderBy:'name'"
							style="cursor: pointer; margin-left: 30px;">
							<div class="form-group">
								<input id="additional_{{additionalOption}}" type="checkbox"
									ng-click="onAdditionalOptionClick(additionalOption)" />
								{{additionalOption}}
							</div>
						</div>
					</div>
					<div class="panel-heading">
						<span class="lead">Add your SSH keys <span
							class="glyphicon glyphicon-question-sign" title="SSH Key Help"></span></span>
					</div>
					<div class="row tab-pane fade in">
						<button type="button" class="btn btn-primary"
							style="color: #6b6b70; background: #cccccc; margin-left: 30px;"
							ng-click="onSSHKeyClick('New SSH Key')">New SSH Key</button>
						<br />
						<div class="col-md-2 media"
							ng-repeat="sshKey in referenceData.sshKeyList | orderBy:'name'"
							style="cursor: pointer; margin-left: 30px;">
							<div class="form-group">
								<input id="sshKey_{{sshKey}}" type="checkbox" ng-click="onSSHKeyClick(sshKey)">
								{{sshKey}}
							</div>
						</div>
					</div>
					<div class="row tab-pane fade in">
						<div class="col-md-5">
							<div class="panel-heading">
								<span class="lead">Finalize and create</span>
							</div>
							<input type="number" ng-model="droplet.numberOfDroplets"
								style="text-aligh: right;" value="1" /> Droplet
						</div>
						<div class="col-md-5">
							<div class="panel-heading">
								<span class="lead">Choose a hostname</span>
							</div>
							<div class="form-group">
								<input type="text" ng-model="droplet.hostname"
									style="margin-left: 30px; width: 300px;" />
							</div>
						</div>
					</div>
					<button type="button" class="btn btn-block"
						ng-click="onSaveDroplets()"
						style="background: #15cd72; margin-top: 50px;">{{droplet.id > 0 ? 'Update' : 'Create'}}</button>
					<div class="row tab-pane fade in">
						<div id="error_container" class="col-md-5 form-group"
							style="color: red; margin-left: 10px;"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
	<script src="<c:url value='/web/js/app.js' />"></script>
	<script src="<c:url value='/web/js/controller/controllers.js' />"></script>
	<script src="<c:url value='/web/js/service/services.js' />"></script>
</body>
</html>