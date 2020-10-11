load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

skylib_version = "0.8.0"
http_archive(
    name = "bazel_skylib",
    type = "tar.gz",
    url = "https://github.com/bazelbuild/bazel-skylib/releases/download/{}/bazel-skylib.{}.tar.gz".format (skylib_version, skylib_version),
    sha256 = "2ef429f5d7ce7111263289644d233707dba35e39696377ebab8b0bc701f7818e",
)

rules_scala_version="a2f5852902f5b9f0302c727eead52ca2c7b6c3e2"
http_archive(
    name = "io_bazel_rules_scala",
    strip_prefix = "rules_scala-%s" % rules_scala_version,
    type = "zip",
    url = "https://github.com/bazelbuild/rules_scala/archive/%s.zip" % rules_scala_version,
    sha256 = "8c48283aeb70e7165af48191b0e39b7434b0368718709d1bced5c3781787d8e7",
)


load("@io_bazel_rules_scala//scala:toolchains.bzl", "scala_register_toolchains")
scala_register_toolchains()

load("@io_bazel_rules_scala//scala:scala.bzl", "scala_repositories")
scala_repositories()

graal_version="15f7e345e848764725897b74bd0cdb59282f2057"
http_archive(
    name = "rules_graal",
    sha256 = "ceac935b2ba685745caabd1c3c5b14adefa235d507e8db547ba7ce83bcb03ea5",
    strip_prefix = "rules_graal-%s" % graal_version,
    urls = [
        "https://github.com/andyscott/rules_graal/archive/%s.zip" % graal_version,
    ],
)

load("@rules_graal//graal:graal_bindist.bzl", "graal_bindist_repository")
graal_bindist_repository(
    name = "graal",
    java_version = "11",
    version = "19.3.1",
)

jvm_external_rules_version = "3.3"
http_archive(
    name = "rules_jvm_external",
    sha256 = "d85951a92c0908c80bd8551002d66cb23c3434409c814179c0ff026b53544dab",
    strip_prefix = "rules_jvm_external-%s" % jvm_external_rules_version,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % jvm_external_rules_version
)

load("@rules_jvm_external//:defs.bzl", "maven_install")
maven_install(
    artifacts = [
        "org.tmatesoft.svnkit:svnkit:1.3.1"
    ],
    fetch_sources = True,
    maven_install_json = "//:maven_install.json",
    repositories = [
        "https://jcenter.bintray.com/",
        "https://maven.google.com",
        "https://repo1.maven.org/maven2",
    ],
)

load("@maven//:defs.bzl", "pinned_maven_install")
pinned_maven_install()