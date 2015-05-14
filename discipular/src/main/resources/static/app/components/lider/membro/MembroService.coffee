angular.module 'app.service'
.service 'MembroService', ['Restangular', (Restangular) ->
  Restangular.all('membros');
]
