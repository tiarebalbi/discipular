var gulp = require('gulp');
var concat = require('gulp-concat');
var less = require('gulp-less');
var coffee = require('gulp-coffee');
var uglify = require('gulp-uglify');
var minifyCSS = require('gulp-minify-css');
var notify = require("gulp-notify");

var locations = {
    vendor: [
        "bower_components/jquery/dist/jquery.js",

        "bower_components/angular/angular.js",
        "bower_components/angular-route/angular-route.js",

        "bower_components/bootstrap/dist/js/bootstrap.js"
    ],
    css: [
        "bower_components/bootstrap/dist/css/bootstrap.css",
        "bower_components/font-awesome/css/font-awesome.css",
        "src/main/resources/static/compartilhado/css/elements.css",
        "src/main/resources/static/compartilhado/css/layouts.css"
    ],
    app: [
        "src/main/resources/static/app/app.coffee",
        "src/main/resources/static/app/components/ControllerProvider.coffee",
        "src/main/resources/static/app/components/**/*.coffee"
    ],
    asserts: "src/main/resources/static/"

};

gulp.task('vendor', function () {
    gulp.src(locations.vendor)
        .pipe(uglify({
            outSourceMap: true
        }))
        .pipe(gulp.dest(locations.asserts + "vendor"))
        .pipe(notify("Vendor Compilados"));
});

gulp.task('styles', function () {
    gulp.src(locations.css)
        .pipe(minifyCSS())
        .pipe(concat('theme.css'))
        .pipe(gulp.dest(locations.asserts + "css"))
        .pipe(notify("Styles Compilados"))
});

gulp.task('watch', function () {
    gulp.watch(locations.css, ['styles']);
    gulp.watch(locations.vendor, ['vendor']);
    gulp.watch(locations.app, ['application']);
    gulp.src(locations.css)
        .pipe(notify("Iniciando monitoramento de alterações"));
});

gulp.task('application', function () {
    gulp.src(locations.app)
        .pipe(coffee())
        .pipe(uglify({
            outSourceMap: true
        }))
        .pipe(concat('application.js'))
        .pipe(gulp.dest(locations.asserts + "js"))
        .pipe(notify("Application Compilados"));
});


gulp.task('default', ['vendor', 'styles', 'application']);
