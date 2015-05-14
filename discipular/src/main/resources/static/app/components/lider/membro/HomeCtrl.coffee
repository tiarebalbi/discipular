angular.module 'app.controller'
.controller 'HomeCtrl', ['$scope', 'MembroService', ($scope, membroService) ->
  $scope.form = false
  $scope.continueSaving = false
  $scope.membro = {}
  $scope.membros = []

  $scope.showForm = ->
    $scope.form = true

  $scope.hiddenForm = ->
    $scope.form = false

  $scope.listar = ->
    membroService._getList().then (response) ->
      console.log response
      $scope.membros = response

  $scope.salvar = ->

    if !$scope.continueSaving
      $scope.hiddenForm()
      $scope.membros.push $scope.membro

  $scope.listar()


]