#include <ctime>
#include <math.h>
#include "common/cg_common.h"
#include "main.h"
#include "primitives.h"
Vector eye;
int solution[5];
int choice[5][10], verifier[5][10];
float arrow_x = -3.5, arrow_y, arrow_z;
bool mvmt_arrow = true, p, g;
int main(int argc, char** argv) {
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_RGBA);
    glutInitWindowSize(800, 600);
    glutCreateWindow("Master Mind");
	
	glEnable(GL_DEPTH_TEST);
	//glEnable(GL_LIGHTING);
	glEnable(GL_LINE_SMOOTH);
	
	glClearColor(0, 0, 0, 1);
	srand(time(0));
	reset();
	glutDisplayFunc(display);
    glutReshapeFunc(reshape);
	//glutIdleFunc(idle);
    glutKeyboardFunc(key);
    
	glutMainLoop();
}
int frame_count = 0;
void idle(){
	frame_count = ++frame_count % 200;
	if (frame_count == 0)
		display();
}
void display() {
	glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glMatrixMode (GL_MODELVIEW);
	glLoadIdentity ();
	gluLookAt (9 * sin(eye.x), eye.y,  9 * cos(eye.z), 0, 0, 0, 0, 10, 0);
	showField();
	showChoice();
	if (g || p) showCode();
	showVerification();
	showArrow();
	gagner();
	perdu();
	reset();
	showKeys();
	glutSwapBuffers();
}
void key(unsigned char k,int,int) {
	switch (k) {
		case 27:
			exit(0);
			break;
		case 'd':
			eye.x += 0.02;
			eye.z = eye.x;
			display();
			break;
		case 'a':
			eye.x -= 0.02;
			eye.z = eye.x;
			display();
			break;
		case 'w':
			if (eye.y < 10){
				eye.y += 0.2;
				display();
			}
			break;
		case 's':
			if (eye.y > -10) {
				eye.y -= 0.2;
				display();
			}
			break;
		case ' ':
			eye.x = eye.y = eye.z = 0;
			display();
			break;
		case 'r':
			reset();
			display();
			break;
		default:
			choose(k);
			display();
			break;
	}
}
void choose(int key) {
	bool done = false;
	for (int b = 0; b < 10 && !done; b++) {
		for (int a = 0; a < 5 && !done; a++) {
			if (choice[a][b] == 0) {
				switch (key) {
					case 'y': choice[a][b] = red;	 break;
					case 'x': choice[a][b] = blue;	 break;
					case 'c': choice[a][b] = green;	 break;
					case 'v': choice[a][b] = yellow; break;
					case 'b': choice[a][b] = violet; break;
					case 'n': choice[a][b] = orange; break;
					case 'm': choice[a][b] = brown;	 break;
					default: break;
				}
				if (choice[a][b] == solution[a] && choice[a][b] != 0)
					verifier[a][b] = 1;
				done = true;
			}
		}
	}
}
void reshape(int width, int height) {
	glViewport(0, 0, width, height); 
	glMatrixMode(GL_PROJECTION); 
	glLoadIdentity(); 
	gluPerspective(45, (float)width/(float)height, 1, 100);
    display();
}
void reset() {
	g = p = false;
	for (int a = 0; a < 5; a++)
		solution[a] = 1 + rand() % 7;
	for (int b = 0; b < 5; b++) {
		for (int c = 0; c < 10; c++) {
			choice[b][c] = verifier[b][c] = 0;
		}
	}	
}
void showField() {
	glColor4d(1,1,1,1);
	glBegin(GL_QUADS);
		glVertex3f(3,3,-2);
		glVertex3f(-3,3,-2);
		glVertex3f(-3,-3,0);
		glVertex3f(3,-3,0);
	glEnd();	
	glColor4d(.95,.95,.95,1);
	glBegin(GL_QUADS);
		glVertex3f(3,3,-3);
		glVertex3f(-3,3,-3);
		glVertex3f(-3,-3,-1);
		glVertex3f(3,-3,-1);
	glEnd();
	glBegin(GL_QUADS);
		glVertex3f(3,3,-2);
		glVertex3f(3,3,-3);
		glVertex3f(-3,3,-3);
		glVertex3f(-3,3,-2);
	glEnd();
	glBegin(GL_QUADS);
		glVertex3f(3,-3,0);
		glVertex3f(3,-3,-1);
		glVertex3f(-3,-3,-1);
		glVertex3f(-3,-3,0);
	glEnd();
	glBegin(GL_QUADS);
		glVertex3f(-3,3,-2);
		glVertex3f(-3,3,-3);
		glVertex3f(-3,-3,-1);
		glVertex3f(-3,-3,0);
	glEnd();
	glBegin(GL_QUADS);
		glVertex3f(3,3,-2);
		glVertex3f(3,3,-3);
		glVertex3f(3,-3,-1);
		glVertex3f(3,-3,0);
	glEnd();
}
void showChoice() {
	for (int a = 0; a < 5; a++) {
		for (int b = 0; b < 10; b++) {
			chooseColor(choice[a][b]);
			if (choice[a][b] != 0)
				drawSphere(a/1.5 - 2.5, b/1.99 - 2.75, -(b/1.99 - 2.75)/3 - 1, 10, 10, 0.2);
		}
	}
}
void chooseColor(int c) {
	switch (c){
		case red:	glColor4d(1.00,0.00,0.00,1.00);	break;
		case blue:	glColor4d(0.00,0.00,1.00,1.00);	break;
		case green:	glColor4d(0.00,1.00,0.00,1.00);	break;
		case yellow:glColor4d(1.00,1.00,0.00,1.00);	break;
		case violet:glColor4d(1.00,0.00,1.00,1.00); break;
		case orange:glColor4d(1.00,0.50,0.25,1.00);	break;
		case brown:	glColor4d(0.50,0.25,0.00,1.00);	break;
	}
}
void showCode () {
	for (int a = 0; a < 5; a++) {	
		chooseColor(solution[a]);
		if (solution[a] != 0) {
			drawSphere(a/1.5 - 2.5, 2.777, -1.926, 10, 10, 0.2);
		}
	}
	
}
void showVerification(){
	for (int a = 0; a < 5; a++) {
		for (int b = 0; b < 10; b++) {
			if (verifier[a][b] == 1) {
				glColor4d (0,0,0,0);
				drawSphere (a/3.1 + 1.2, b/1.99 - 2.75, -(b/1.99 - 2.75)/3 - 1, 10, 10, 0.1);
			}
		}
	}
}
void showArrow() {
	int i = 0;
	bool final = false;
	for (int b = 0; b < 10 && !final; b++) {
		for (int a = 0; a < 5 && !final; a++) {
			if (choice[a][b] == 0) {
				i = b;
				final = true;
			}
		}
	}
	if (mvmt_arrow)
		arrow_x -= 0.02;
	if (mvmt_arrow == false) 
		arrow_x += 0.02;
	if (arrow_x <= -5)
		mvmt_arrow = false;
	if (arrow_x >= -3.5)
		mvmt_arrow = true;
	if (i < 10) {
		arrow_y = i/1.99 - 2.75;
		arrow_z = -arrow_y/3 - 1;
	}
	drawArrow(arrow_x, arrow_y, arrow_z);
}
void gagner() {
	for (int b = 0; b < 10; b++) {
		for (int a = 0; a < 5; a++) {
			if (verifier[a][b] != 1)
				break;
			if (a == 4)
				g = true;
		}
	}
	if (g) {
		glColor4d(0,0,0,1);
		drawText(250, 200, "gagner");
	}
}
void perdu() {
	if (choice[4][9] != 0)
		p = true;
	if (p) {
		glColor4d(0,0,0,1);
		drawText(250, 200, "perdu");
	}
}
void showKeys() {
	glColor4d(1, 0, 0, 1);
	drawText(120, -90, "'r' restart");
	drawText(300, -90, "'F1' = red");
	drawText(430, -90, "'F2' = blue");
	drawText(560, -90, "'F3' = green");
	drawText(690, -90, "'F4' = yellow");
	drawText(300, -60, "'F5' = violet");
	drawText(430, -60, "'F6' = orange");
	drawText(560, -60, "'F7' = brown");
}