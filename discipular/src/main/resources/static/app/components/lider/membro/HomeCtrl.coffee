angular.module 'app.controller'
.controller 'HomeCtrl', ['$scope', '$routeParams', ($scope) ->
  $scope.form = false
  $scope.continueSaving = false
  $scope.membro = {}
  $scope.membros = []

  $scope.showForm = ->
    $scope.form = true

  $scope.hiddenForm = ->
    $scope.form = false

  $scope.salvar = ->

    if !$scope.continueSaving
      $scope.hiddenForm()

      #validar

      $scope.membros.push $scope.membro

      #salvar

]