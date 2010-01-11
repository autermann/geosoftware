!exists(common/cg_common.pri): error(Could not find cg_common.pri!)
include(common/cg_common.pri)

# add additional source files here
SOURCES += main.cpp
HEADERS += main.h \
	primitives.h