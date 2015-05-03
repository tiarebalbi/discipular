angular.module 'app', ['ngRoute', 'app.controller']

.config ['$routeProvider', ($routeProvider) ->
  $routeProvider
  .when '/', {
    controller: 'HomeCtrl',
    templateUrl: '/app/components/home/index.html'
  }
  .otherwise {
    redirectTo: '/'
  }


]

