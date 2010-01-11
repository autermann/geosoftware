#ifndef CG_COMMON_H
#define CG_COMMON_H

// some common includes
#include <cmath>
#include <cstdlib>

#if defined(WIN32) && defined(_MSC_VER)
#include <windows.h>
#endif

#if defined(WIN32) && defined(__GNUC__)
// prevent some warnings when using MinGW
#define GLUT_NO_LIB_PRAGMA
#define GLUT_NO_WARNING_DISABLE
#define GLUT_DISABLE_ATEXIT_HACK
#endif

#ifdef __APPLE__
#include <OpenGL/gl.h>  // The GL Header File
#include <GLUT/glut.h>  // The GL Utility Toolkit (GLUT) Header
#else
#include <GL/gl.h>      // The GL Header File
#include <GL/glut.h>    // The GL Utility Toolkit (GLUT) Header
#endif

#endif // CG_COMMON_H
