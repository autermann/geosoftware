TEMPLATE = app
CONFIG += console
QT -= core gui
QT += opengl

unix {
    # add "_bin" to target filename
    TARGET = $${TARGET}_bin
}

unix : !mac {
    LIBS += -lglut
}

mac {
    CONFIG -= app_bundle
    LIBS += -framework GLUT
}

win32 {
    # add GLUT includes and library (dynamically linked)
    GLUTDIR = ..\common\glut\windows
    INCLUDEPATH += $$GLUTDIR/include
    LIBS += $$GLUTDIR/lib/glut32.lib

    # copy GLUT DLL into the output directory
    GLUTDLL = $$GLUTDIR\lib\glut32.dll
    glutdll_debug.target = debug\glut32.dll
    glutdll_debug.commands = copy $$GLUTDLL debug
    glutdll_release.target = release\glut32.dll
    glutdll_release.commands = copy $$GLUTDLL release
    QMAKE_EXTRA_TARGETS += glutdll_debug glutdll_release
    POST_TARGETDEPS += debug\glut32.dll release\glut32.dll
}

HEADERS += cg_common.h
