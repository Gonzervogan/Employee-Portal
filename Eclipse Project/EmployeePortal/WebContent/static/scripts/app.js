angular.module('MainApp',['ngRoute','ngCookies'])
.config(['$routeProvider','$locationProvider',function($routeProvider,$locationProvider){
    $locationProvider.hashPrefix('');
    $routeProvider.when('/',{
        templateUrl:'static/views/LoginPage.html',
        controller:'LoginController as lgctrl',
        resolve:{
            auth:['$location','$rootScope',
                function($location,$rootScope){
                    if($rootScope.globals.currentUser){
                        $location.path('/Profile');
                    }else{
                        return true;
                    }
                }]
        }
    })
    .when('/Profile',{
        templateUrl:'static/views/Profile.html',
        controller:'ProfileController as prctrl',
        resolve:{
            auth:['$location','$rootScope',
                function($location,$rootScope){
                    if(!$rootScope.globals.currentUser){
                        $location.path('/');
                    }else{
                        return true;
                    }
                }]
        }
    })
    .when('/CompanyDetails',{
        templateUrl:'static/views/Details.html',
        controller:'DetailsController as dtctrl',
        resolve:{
            auth:['$location','$rootScope',
                function($location,$rootScope){
                    if(!$rootScope.globals.currentUser){
                        $location.path('/');
                    }else{
                        return true;
                    }
                }]
        }
    });
    $routeProvider.otherwise({
      redirectTo:'/'
    });
}])
.run(['$rootScope','$cookieStore',function($rootScope,$cookieStore){
    $rootScope.globals=$cookieStore.get('globals')||{};
    $rootScope.api='http://localhost:8080/EmployeePortal/rest/';
}]);
