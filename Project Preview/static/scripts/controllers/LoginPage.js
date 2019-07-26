angular.module('MainApp')
.controller('LoginController',['AuthenticationService','$rootScope','$http','$location',function(AuthenticationService,$rootScope,$http,$location){
    var self=this;
    self.login=function(){
        AuthenticationService.login(self.user.username,self.user.password,function(response){
            if(response.success){
                $location.path('/Profile');
            }else{
                self.error=response.message;
            }
        });
    };
}]);
