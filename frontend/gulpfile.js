'use strict';
require('es6-promise').polyfill();
var gulp = require('gulp');
var gutil = require('gulp-util');
var ftp = require('vinyl-ftp');
var $ = require('gulp-load-plugins')();
var vulcanize = require('gulp-vulcanize');
//var bundler = require('polymer-bundler');
var sourcemaps = require('gulp-sourcemaps');
var htmlmin = require('gulp-html-minifier');
var gulpif = require('gulp-if');
var cleanCSS = require('gulp-clean-css');
var crisper = require('gulp-crisper');
var uglify = require('gulp-uglify');
var minify = require('gulp-minify');
var jsmin = require('gulp-jsmin');
var rename = require("gulp-rename");
var babel = require('gulp-babel');
var del = require('del');
var pump = require('pump');
var runSequence = require('run-sequence');
var browserSync = require('browser-sync');
//var htmlSplitter = require('polymer-build').HtmlSplitter;
var reload = browserSync.reload;
var path = require('path');
var historyApiFallback = require('connect-history-api-fallback');
var AUTOPREFIXER_BROWSERS = ['ie >= 10', 'ie_mob >= 10', 'ff >= 30', 'chrome >= 34', 'safari >= 7', 'opera >= 23', 'ios >= 7', 'android >= 4.4', 'bb >= 10'];
var DIST = 'dist';
var dist = function(subpath) {
    return !subpath ? DIST : path.join(DIST, subpath);
};
gulp.task('clean', function() {
    return del(['.tmp', dist()]);
});
gulp.task('styles', function() {
    return gulp.src('app/styles/**/*.css').pipe($.minifyCss()).pipe(gulp.dest(dist('styles'))).pipe($.size({
        title: 'styles'
    }));
});
gulp.task('images', function() {
    return gulp.src('app/images/**/*').pipe($.imagemin({
        progressive: true,
        interlaced: true
    })).pipe(gulp.dest(dist('images'))).pipe($.size({
        title: 'images'
    }));
});
gulp.task('lib', function() {
    return gulp.src('src/scripts/**/*.js').pipe(sourcemaps.init()).pipe(concat('app.js')).pipe(sourcemaps.write()).pipe(gulp.dest(dist('scripts')));
});
gulp.task('copy', ['clean'], function() {
    var app = gulp.src(['app/*.*'], {
        dot: true
    }).pipe(gulp.dest(dist()));
    var images = gulp.src(['app/images/**/*'], {
        dot: true
    }).pipe(gulp.dest(dist('images')));
    var styles = gulp.src(['app/styles/app.css'], {
        dot: true
    }).pipe(gulp.dest(dist('styles')));
    var scripts = gulp.src(['app/scripts/app.js'], {
        dot: true
    }).pipe(gulp.dest(dist('scripts')));
});
gulp.task('bundle', function() {
    runSequence('vulcanize', 'minHtml', 'minJs');
});
gulp.task('vulcanize', function() {
    return gulp.src('app/elements/elements.html').pipe($.vulcanize({
        stripComments: true,
        inlineCss: true,
        inlineScripts: true,
        sourcemaps: true
        // "excludes": {
        // }
    })).pipe(crisper({
        scriptInHead: false,
        onlySplit: false
    })).pipe(gulp.dest(dist('elements')));
});
gulp.task('minHtml', function() {
    return gulp.src('dist/elements/elements.html').pipe(htmlmin({
        collapseWhitespace: true,
        conservativeCollapse: true,
        minifyJS: true,
        minifyCSS: true,
        removeComments: true,
        removeCommentsFromCDATA: true,
        removeCDATASectionsFromCDATA: true
    })).pipe(gulp.dest(dist('elements'), {
        overwrite: true
    }));
});
gulp.task('minJs', function(cb) {
    return gulp.src('dist/elements/elements.js').pipe(jsmin()).pipe(gulp.dest(dist('elements'), {
        overwrite: true
    }));
});
gulp.task('build', function() {
    runSequence(
        ['copy', 'bundle']);
});
gulp.task('serve', function() {
    browserSync({
        port: 5000,
        notify: false,
        logPrefix: 'PSK',
        snippetOptions: {
            rule: {
                match: '<span id="browser-sync-binding"></span>',
                fn: function(snippet) {
                    return snippet;
                }
            }
        },
        // https: true,
        server: {
            baseDir: ['.tmp', 'app'],
            middleware: [historyApiFallback()]
        }
    });
    gulp.watch(['app/**/*.html', '!app/bower_components/**/*.html'], reload);
    gulp.watch(['app/styles/**/*.css'], ['styles', reload]);
    gulp.watch(['app/libs/**/*.js'], reload);
    gulp.watch(['app/scripts/**/*.js'], reload);
    gulp.watch(['app/images/**/*'], reload);
});
gulp.task('serve:dist', function() {
    browserSync({
        port: 5001,
        notify: false,
        logPrefix: 'PSK',
        // https: true,
        server: dist(),
        middleware: [historyApiFallback()]
    });
});
gulp.task('default', function(cb) {
    //runSequence(['copy', 'styles'], ['images', 'fonts', 'html'], 'vulcanize', cb);
});
gulp.task('deploy', function() {
    var conn = ftp.create({
        host: '192.168.33.10',
        user: 'root',
        password: 'vagrant',
        parallel: 10,
        log: gutil.log,
        secure: true
    });
    var globs = ['dist/**/*'];
    return gulp.src(globs, {
            base: '.',
            buffer: false
        })
        //.pipe( conn.newer( '/public_html' ) ) // only upload newer files 
        .pipe(conn.dest('/usr/local/server/tomcat/webapps/aus'));
});