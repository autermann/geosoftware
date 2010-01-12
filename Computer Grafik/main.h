#ifndef MAIN_H
#define MAIN_H

#include <ctime>
#include <math.h>
#include <cstdio>
#include <iostream>
#include "common/cg_common.h"

#define red 1
#define blue 2
#define green 3
#define yellow 4
#define violet 5
#define orange 6
#define brown 7
#define PI 3.14159265

using namespace std;
void menu(int i);
void menuColor(int i);
int main(int, char**);
void showArrow();
void showField();
void showChoice();
void showCode();
void showVerification();
void chooseColor(int);
int getColorFromKeyCode(int);
void drawCube(double, double, double, double);
void drawSphere(double, double, double, double);
void drawArrow(float, float, float);
void key(unsigned char, int, int);
void display();
void idle();
void reshape(int, int);
void choose(int);
void isEnd();
void reset();

struct Vector {
	float x, y, z; 
	Vector () {
		x = 0;
		y = 0;
		z = 0;
	}
};

#endif