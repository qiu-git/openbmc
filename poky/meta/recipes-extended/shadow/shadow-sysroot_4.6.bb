SUMMARY = "Shadow utils requirements for useradd.bbclass"
HOMEPAGE = "http://github.com/shadow-maint/shadow"
BUGTRACKER = "http://github.com/shadow-maint/shadow/issues"
SECTION = "base utils"
LICENSE = "BSD-3-Clause | Artistic-1.0"
LIC_FILES_CHKSUM = "file://login.defs_shadow-sysroot;md5=25e2f2de4dfc8f966ac5cdfce45cd7d5"

DEPENDS = "base-passwd"

PR = "r3"

# The sole purpose of this recipe is to provide the /etc/login.defs
# file for the target sysroot - needed so the shadow-native utilities
# can add custom users/groups for recipes that use inherit useradd.
SRC_URI = "file://login.defs_shadow-sysroot"

S = "${WORKDIR}"

do_install() {
	install -d ${D}${sysconfdir}
	install -p -m 644 ${S}/login.defs_shadow-sysroot ${D}${sysconfdir}/login.defs
}

SYSROOT_DIRS += "${sysconfdir}"

# don't create any packages
# otherwise: dbus-dev depends on shadow-sysroot-dev which depends on shadow-sysroot
# and this has another copy of /etc/login.defs already provided by shadow
PACKAGES = ""

inherit nopackages
