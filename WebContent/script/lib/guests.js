/**
 * Created by Niharika on 10/21/2015.
 */

(function() {
    'use strict';

    angular
        .module('myApp')
        .service('employeeService', employeeService);

    employeeService.$inject = ['$q', '$http'];

    function employeeService($q, $http) {
        var self = this;

        self.getGuests = getGuests;
        self.getGuestById = getGuestById;
        self.createGuest = createGuest;
        //self.delGuest() = deleteGuest;


        //private members
        function getGuests() {

            var defer = $q.defer();

            $http
                .get('http://localhost:8080/Project-API/api/guests')
                .then(successFn, errorFn);

            function successFn(response) {
                defer.resolve(response.data);
            }

            function errorFn(error) {
                defer.reject(error.statusText);
            }

            return defer.promise;
        }



        function getGuestById(id) {
            var defer = $q.defer();

            $http
                .get('http://localhost:8080/Project-API/api/guests' + id)
                .then(successFn, errorFn);

            function successFn(response) {
                defer.resolve(response.data);
            }

            function errorFn(error) {
                defer.reject(error.statusText);
            }

            return defer.promise;
        }

        function createGuest(guest) {
            var defer = $q.defer();

            $http
                .post('http://localhost:8080/Project-API/api/guests', guest)
                .then(successFn, errorFn);

            function successFn(response) {
                defer.resolve(response.data);
            }

            function errorFn(error) {
                defer.reject(error.statusText);
            }

            return defer.promise;
        }

        function deleteEmployee(id) {


        }
    }
})();