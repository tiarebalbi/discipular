var gulp = require('gulp');
var less = require('gulp-less');
var concat = require('gulp-concat');
var coffee = require('gulp-coffee');
var uglify = require('gulp-uglify');
var minifyCSS = require('gulp-minify-css');
var notify = require("gulp-notify");

var locations = {
    vendor: [
        "bower_components/jquery/dist/jquery.js",

        "bower_components/angular/angular.js",
        "bower_components/angular-route/angular-route.js",
        "bower_components/restangular/dist/restangular.js",

        "bower_components/bootstrap/dist/js/bootstrap.js",
    ],
    jsTheme: [
        "src/main/resources/static/app/resources/js/jquery-1.11.1.min.js",
        "src/main/resources/static/app/resources/js/jquery-ui.min.js",
        "src/main/resources/static/app/resources/js/main.js",
        "src/main/resources/static/app/resources/js/utility.js"
    ],
    css: [
        "bower_components/bootstrap/dist/css/bootstrap.css",
        "bower_components/font-awesome/css/font-awesome.css",

        //tema
        "src/main/resources/static/app/resources/css/theme.css",
        "src/main/resources/static/app/resources/css/admin-forms.css"
    ],
    app: [
        "src/main/resources/static/app/app.coffee",
        "src/main/resources/static/app/components/ControllerProvider.coffee",
        "src/main/resources/static/app/components/ServiceProvider.coffee",
        "src/main/resources/static/app/components/lider/membro/MembroService.coffee",
        "src/main/resources/static/app/components/**/*.coffee"
    ],
    fontsGlyphicons: [
        "bower_components/bootstrap/fonts/glyphicons-halflings-regular.woff2",
        "bower_components/bootstrap/fonts/glyphicons-halflings-regular.woff",
        "bower_components/bootstrap/fonts/glyphicons-halflings-regular.ttf",
    ],
    fontsAwesome: [
        "bower_components/font-awesome/fonts/fontawesome-webfont.ttf",
        "bower_components/font-awesome/fonts/fontawesome-webfont.woff",
        "bower_components/font-awesome/fonts/fontawesome-webfont.woff2",
    ],
    asserts: "src/main/resources/static/"

};

gulp.task('fonts', function () {
    gulp.src(locations.fontsGlyphicons)
        .pipe(gulp.dest(locations.asserts + "fonts/glyphicons"))
        .pipe(notify("Fonts Glyphicons Compiladas"))
    gulp.src(locations.fontsAwesome)
        .pipe(gulp.dest(locations.asserts + "fonts/font-awesome"))
        .pipe(notify("Fonts Awesome Compiladas"))
});

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
    gulp.watch(locations.jsTheme, ['themejs']);
    gulp.watch(locations.fontsAwesome, ['fonts']);
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

gulp.task('themejs', function () {
    gulp.src(locations.jsTheme)
        .pipe(uglify({
            outSourceMap: true
        }))
        .pipe(concat('theme.js'))
        .pipe(gulp.dest(locations.asserts + "js"))
        .pipe(notify("Theme js Compilados"));
});

gulp.task('default', ['vendor', 'styles', 'application', 'fonts', 'themejs']);
