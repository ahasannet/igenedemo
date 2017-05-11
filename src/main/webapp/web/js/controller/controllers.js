angular.module('igeneApp').controller('IgeneController', ['$scope', 'IgeneService', function($scope, IgeneService) {

    $scope.errorMessage = "";
    $scope.droplet = {distribution: 0, version: "", configuration: 0, serverType: "", dataCenter: 0, slot: 0, applications: [], additionalOptions: [], sshKeys: [], numberOfDroplets: 0, hostname: ""};

    referenceData();

    getDropletList();

    function getDropletList() {
        IgeneService.getDropletList()
            .then(
                function(response) {
                    $scope.dropletList = response;
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                }
            );
    }

    function referenceData() {
        IgeneService.referenceData()
            .then(
                function(response) {
                    $scope.referenceData = response;

                    for(var i=0; i<$scope.referenceData.distributionList.length; i++)
                    {
                        var distribution = $scope.referenceData.distributionList[i];
                        if(distribution.versions.length > 0)
                        {
                            $scope.droplet.version = distribution.versions[0];
                            break;
                        }
                    }

                },
                function(errResponse){
                    console.error('Error while fetching Users');
                }
            );
    }

    $scope.onDistributionClick = function(id) {
        var distribution;
        for(var i=0; i<$scope.referenceData.distributionList.length; i++)
        {
            if($scope.referenceData.distributionList[i].id == id)
            {
                distribution = $scope.referenceData.distributionList[i];
            }
            document.getElementById("distribution_div_" + $scope.referenceData.distributionList[i].id).style = "width: 104px; border-style: solid; border-color: #cccccc; border-width: 2px;";
            document.getElementById("distribution_hr_" + $scope.referenceData.distributionList[i].id).style = "border-top: 2px solid #cccccc;";
        }
        if(id > 0)
        {
            document.getElementById("distribution_div_" + id).style = "width: 104px; border-style: solid; border-color: #0069ff; border-width: 2px;";
            document.getElementById("distribution_hr_" + id).style = "border-top: 2px solid #0069ff;";
            if($scope.droplet.distribution != id)
            {
                $scope.droplet.version = distribution.versions[0];
            }
            $scope.droplet.distribution = id;
        }
    };

    $scope.onAppplicationClick = function(id) {
        var isFound = false;
        for(var i=0; i < $scope.droplet.applications.length; i++)
        {
            if($scope.droplet.applications[i] == id)
            {
                $scope.droplet.applications.splice(i, 1);
                isFound = true;
                break;
            }
        }
        if(isFound)
        {
            document.getElementById("application_div_" + id).style = "width: 104px; border-style: solid; border-color: #cccccc; border-width: 2px;";
            document.getElementById("application_hr_" + id).style = "border-top: 2px solid #cccccc;";
        }
        else
        {
            $scope.droplet.applications.push(id);
            document.getElementById("application_div_" + id).style = "width: 104px; border-style: solid; border-color: #0069ff; border-width: 2px;";
            document.getElementById("application_hr_" + id).style = "border-top: 2px solid #0069ff;";
        }
    };

    $scope.onServerConfigurationClick = function(id) {
        for(var i=0; i<$scope.referenceData.serverConfigurationList.length; i++)
        {
            document.getElementById("standard_server_div_" + $scope.referenceData.serverConfigurationList[i].id).style = "width: 150px; border-style: solid; border-color: #cccccc; border-width: 2px;";
            document.getElementById("standard_server_hr_" + $scope.referenceData.serverConfigurationList[i].id).style = "border-top: 2px solid #cccccc;";
        }
        for(var i=0; i<$scope.referenceData.highMemoryServerList.length; i++)
        {
            document.getElementById("high_memory_server_div_" + $scope.referenceData.highMemoryServerList[i].id).style = "width: 150px; border-style: solid; border-color: #cccccc; border-width: 2px;";
            document.getElementById("high_memory_server_hr_" + $scope.referenceData.highMemoryServerList[i].id).style = "border-top: 2px solid #cccccc;";
        }
        if(id > 0)
        {
            document.getElementById("standard_server_div_" + id).style = "width: 150px; border-style: solid; border-color: #0069ff; border-width: 2px;";
            document.getElementById("standard_server_hr_" + id).style = "border-top: 2px solid #0069ff;";
            $scope.droplet.configuration = id;
            $scope.droplet.serverType = 1;
        }
    };

    $scope.onHighMemoryServerClick = function(id) {
        for(var i=0; i<$scope.referenceData.serverConfigurationList.length; i++)
        {
            document.getElementById("standard_server_div_" + $scope.referenceData.serverConfigurationList[i].id).style = "width: 150px; border-style: solid; border-color: #cccccc; border-width: 2px;";
            document.getElementById("standard_server_hr_" + $scope.referenceData.serverConfigurationList[i].id).style = "border-top: 2px solid #cccccc;";
        }
        for(var i=0; i<$scope.referenceData.highMemoryServerList.length; i++)
        {
            document.getElementById("high_memory_server_div_" + $scope.referenceData.highMemoryServerList[i].id).style = "width: 150px; border-style: solid; border-color: #cccccc; border-width: 2px;";
            document.getElementById("high_memory_server_hr_" + $scope.referenceData.highMemoryServerList[i].id).style = "border-top: 2px solid #cccccc;";
        }
        if(id > 0)
        {
            document.getElementById("high_memory_server_div_" + id).style = "width: 150px; border-style: solid; border-color: #0069ff; border-width: 2px;";
            document.getElementById("high_memory_server_hr_" + id).style = "border-top: 2px solid #0069ff;";
            $scope.droplet.configuration = id;
            $scope.droplet.serverType = 2;
        }
    };

    $scope.onDataCenterButtonClick = function(id, slot) {
        var dataCenter;
        for(var i=0; i<$scope.referenceData.dataCenterRegionList.length; i++)
        {
            if($scope.referenceData.dataCenterRegionList[i].id == id)
            {
                dataCenter = $scope.referenceData.dataCenterRegionList[i];
            }
            document.getElementById("data_center_div_" + $scope.referenceData.dataCenterRegionList[i].id).style = "width: 104px; border-style: solid; border-color: #cccccc; border-width: 2px;";
            document.getElementById("data_center_hr_" + $scope.referenceData.dataCenterRegionList[i].id).style = "border-top: 2px solid #cccccc;";
            for(var k=0; k<$scope.referenceData.dataCenterRegionList[i].slots.length; k++)
            {
                var style = "";
                if($scope.referenceData.dataCenterRegionList[i].slots.length == 1)
                {
                    style = "width:100px;";
                }
                else if($scope.referenceData.dataCenterRegionList[i].slots.length == 2)
                {
                    style = "width:50px;";
                }
                document.getElementById("data_center_button_" + $scope.referenceData.dataCenterRegionList[i].id + "_" + $scope.referenceData.dataCenterRegionList[i].slots[k]).style = style + "color: #6b6b70; background: #cccccc;";
            }
        }
        if(id > 0 && slot > 0)
        {
            document.getElementById("data_center_div_" + id).style = "width: 104px; border-style: solid; border-color: #0069ff; border-width: 2px;";
            document.getElementById("data_center_hr_" + id).style = "border-top: 2px solid #0069ff;";

            var style = "";
            if(dataCenter.slots.length == 1)
            {
                style = "width:100px;";
            }
            else if(dataCenter.slots.length == 2)
            {
                style = "width:50px;";
            }

            document.getElementById("data_center_button_" + id + "_" + slot).style = style + "color: white; background: #0069ff;";
            $scope.droplet.dataCenter = id;
            $scope.droplet.slot = slot;
        }
    };

    $scope.onAdditionalOptionClick = function(additionalOption) {
        var isFound = false;
        for(var i=0; i < $scope.droplet.additionalOptions.length; i++)
        {
            if($scope.droplet.additionalOptions[i] == additionalOption)
            {
                $scope.droplet.additionalOptions.splice(i, 1);
                isFound = true;
                break;
            }
        }
        if(isFound == false)
        {
            $scope.droplet.additionalOptions.push(additionalOption);
        }
    };

    $scope.onSSHKeyClick = function(sshKey) {
        var isFound = false;
        for(var i=0; i < $scope.droplet.sshKeys.length; i++)
        {
            if($scope.droplet.sshKeys[i] == sshKey)
            {
                $scope.droplet.sshKeys.splice(i, 1);
                isFound = true;
                break;
            }
        }
        if(isFound == false)
        {
            $scope.droplet.sshKeys.push(sshKey);
        }
    };

    $scope.onSaveDroplets = function() {
        $scope.errorMessage = "<br/>";
        var isValid = true;

        if($scope.droplet.distribution == 0)
        {
            isValid = false;
            $scope.errorMessage += "<p>Please choose an image</p>";
        }

        if($scope.droplet.applications.length == 0)
        {
            isValid = false;
            $scope.errorMessage += "<p>Please choose 1-click application</p>";
        }

        if($scope.droplet.configuration == 0)
        {
            isValid = false;
            $scope.errorMessage += "<p>Please choose a size</p>";
        }

        if($scope.droplet.dataCenter == 0)
        {
            isValid = false;
            $scope.errorMessage += "<p>Please a data center region</p>";
        }

        if($scope.droplet.additionalOptions.length == 0)
        {
            isValid = false;
            $scope.errorMessage += "<p>Please select additional option</p>";
        }

        if($scope.droplet.sshKeys.length == 0)
        {
            isValid = false;
            $scope.errorMessage += "<p>Please add your ssh key</p>";
        }

        if($scope.droplet.numberOfDroplets == 0)
        {
            isValid = false;
            $scope.errorMessage += "<p>Please select number of droplet</p>";
        }

        if($scope.droplet.hostname.length == 0)
        {
            isValid = false;
            $scope.errorMessage += "<p>Please enter hostname</p>";
        }

        document.getElementById("error_container").innerHTML = $scope.errorMessage;

        if(isValid)
        {
            console.log($scope.droplet);
            IgeneService.saveDroplet($scope.droplet)
                .then(
                    function(response) {
                        $scope.dropletList = response;

                        for(var i=0; i<$scope.droplet.applications.length; i++)
                        {
                            $scope.onAppplicationClick($scope.droplet.applications[i]);
                        }

                        for(var i=0; i<$scope.droplet.applications.length; i++)
                        {
                            $scope.onAppplicationClick($scope.droplet.applications[i]);
                        }

                        for(var i=0; i<$scope.droplet.applications.length; i++)
                        {
                            $scope.onAppplicationClick($scope.droplet.applications[i]);
                        }

                        console.log($scope.droplet.applications);

                        $scope.droplet = {distribution: 0, version: "", configuration: 0, serverType: "", dataCenter: 0, slot: 0, applications: [], additionalOptions: [], sshKeys: [], numberOfDroplets: 0, hostname: ""};

                        $scope.onDistributionClick($scope.droplet.distribution);

                        $scope.onServerConfigurationClick($scope.droplet.configuration);

                        $scope.onHighMemoryServerClick($scope.droplet.configuration);

                        $scope.onDataCenterButtonClick($scope.droplet.dataCenter, $scope.droplet.slot);

                        for(var i=0; i<$scope.referenceData.additionalOptionList.length; i++)
                        {
                            document.getElementById("additional_" + $scope.referenceData.additionalOptionList[i]).checked = false;
                        }

                        for(var i=0; i<$scope.referenceData.sshKeyList.length; i++)
                        {
                            document.getElementById("sshKey_" + $scope.referenceData.sshKeyList[i]).checked = false;
                        }

                    },
                    function(errResponse){
                        console.error('Error while fetching droplist');
                    }
                );
        }

    };

    $scope.onEditDroplet = function(id) {
        IgeneService.getDroplet(id)
            .then(
                function(response) {
                    $scope.droplet = response;

                    $scope.onDistributionClick($scope.droplet.distribution);

                    var applications = $scope.droplet.applications;

                    $scope.droplet.applications = [];

                    for(var i=0; i<applications.length; i++)
                    {
                        $scope.onAppplicationClick(applications[i]);
                    }

                    if($scope.droplet.serverType == 1)
                    {
                        $scope.onServerConfigurationClick($scope.droplet.configuration);
                    }
                    else
                    {
                        $scope.onHighMemoryServerClick($scope.droplet.configuration);
                    }

                    $scope.onDataCenterButtonClick($scope.droplet.dataCenter, $scope.droplet.slot);

                    var additionalOptions = $scope.droplet.additionalOptions;

                    $scope.droplet.additionalOptions = [];

                    for(var i=0; i<additionalOptions.length; i++)
                    {
                        document.getElementById("additional_" + additionalOptions[i]).checked = true;
                        $scope.onAdditionalOptionClick(additionalOptions[i]);
                    }

                    var sshKeys = $scope.droplet.sshKeys;

                    $scope.droplet.sshKeys = [];

                    for(var i=0; i<sshKeys.length; i++)
                    {
                        document.getElementById("sshKey_" + sshKeys[i]).checked = true;
                        $scope.onSSHKeyClick(sshKeys[i]);
                    }

                },
                function(errResponse){
                    console.error('Error while fetching droplet');
                }
            );
    };


}]);