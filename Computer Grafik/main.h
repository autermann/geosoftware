#ifndef MAIN_H
#define MAIN_H

#define red 1
#define blue 2
#define green 3
#define yellow 4
#define violet 5
#define orange 6
#define brown 7
#define PI 3.14159265

int main(int, char**);

/* callbacks */
void key(unsigned char, int, int);
void display();
void idle();
void reshape(int, int);

/* primitives */
void drawText(int, int, const char);
void drawCube(double, double, double, double);
void drawSphere(double, double, double, double, double, double);
void drawArrow(float, float, float);

/* drawing */
void showArrow();
void showField();
void showChoice();
void showCode();
void showVerification();

/* util */
void chooseColor(int);

/* game */
void choose(int);
void gagner();
void perdu();
void reset();
void showKeys();

struct Vector {float x, y, z; Vector () {x = 0;y = 0;z = 0;}};

#endif