angular.module('igeneApp').factory('IgeneService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/igene-demo/';
    var REFERENCE_DATA_URI = 'droplets/data';
    var DROPLET_URI = 'droplets';

    var factory = {
        referenceData: referenceData,
        getDroplet: getDroplet,
        getDropletList: getDropletList,
        saveDroplet: saveDroplet
    };

    return factory;

    function getDroplet(id) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + DROPLET_URI + "/" + id)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching droplet data');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function getDropletList() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + DROPLET_URI)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching droplet data');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function referenceData() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + REFERENCE_DATA_URI)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching reference data');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function saveDroplet(droplet) {
        var deferred = $q.defer();

        if(droplet.id > 0)
        {
            $http.put(REST_SERVICE_URI + DROPLET_URI, droplet)
                .then(
                    function (response) {
                        deferred.resolve(response.data);
                    },
                    function(errResponse){
                        console.error('Error while creating droplet');
                        deferred.reject(errResponse);
                    }
                );
        }
        else
        {
            $http.post(REST_SERVICE_URI + DROPLET_URI, droplet)
                .then(
                    function (response) {
                        deferred.resolve(response.data);
                    },
                    function(errResponse){
                        console.error('Error while creating droplet');
                        deferred.reject(errResponse);
                    }
                );
        }
        return deferred.promise;
    }

}]);