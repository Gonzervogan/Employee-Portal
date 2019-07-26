angular.module('MainApp')
.controller('NavBarController',['AuthenticationService','$rootScope','$location',function(AuthenticationService,$rootScope,$location){
    var self=this;
    self.logout=function(){
        AuthenticationService.logout(function(){
            $location.path('/');
        });
    };
    /*self.openProfile=function(){
        $location.path('/Profile');
    };*/
}]);
