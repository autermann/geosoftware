#include <ctime>
#include <math.h>
#include "common/cg_common.h"
#include "main.h"
#include "primitives.h"
Vector eye;
int solution[5];
bool verifier[5][10];
int choice[5][10];
float arrow_x = -3.5, arrow_y, arrow_z;
int s = 0, r = 0;
bool win = false, fail = false;
bool mvmt_arrow = true;


int main(int argc, char** argv) {
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_RGBA | GLUT_DOUBLE | GLUT_DEPTH);
    glutInitWindowSize(800, 600);
    glutCreateWindow("MindMaster");
	glEnable(GL_DEPTH_TEST);
	glEnable(GL_LINE_SMOOTH);
	glDepthMask(GL_TRUE);
    glDepthFunc(GL_LESS);
	glShadeModel(GL_SMOOTH);
	glClearColor(0, 0, 0, 1);
	srand(time(0));
	reset();
	glutDisplayFunc(display);
    glutReshapeFunc(reshape);
	glutIdleFunc(idle);
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
	gluLookAt(9 * sin(eye.x), eye.y,  9 * cos(eye.z), 0, 0, 0, 0, 10, 0);
	isEnd();
	showField();
	showChoice();
	if (win || fail) 
		showCode();
	showVerification();
	showArrow();
	showKeys();
	glutSwapBuffers();
	print();
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
			if (!win && !fail){
				choose(k);
				display();
			}
			break;
	}
}
void choose(int key) {
	switch (key) {
		case '1': choice[s][r] = red;		break;
		case '2': choice[s][r] = blue;		break;
		case '3': choice[s][r] = green;		break;
		case '4': choice[s][r] = yellow;	break;
		case '5': choice[s][r] = violet;	break;
		case '6': choice[s][r] = orange;	break;
		case '7': choice[s][r] = brown;		break;
		default: break;
	}
	if (choice[s][r] != 0) {
		if (choice[s][r] == solution[s])
			verifier[s][r] = true;
		s++;
		s %= 5;
		if (s == 0) 
			r++;
	}
}

void print(){
	for (int j = 0; j < 10; j++) {
		for (int i = 0; i < 5; i++) {
			if (verifier[i][j])
				cout << 1;
			else 
				cout << 0;
		}
		cout << endl;
	}
	cout << endl;
}
void reshape(int width, int height) {
	glViewport(0, 0, width, height); 
	glMatrixMode(GL_PROJECTION); 
	glLoadIdentity(); 
	gluPerspective(45, (float)width/(float)height, 1, 100);
    display();
}
void reset() {
	fail = win = false;
	s = r = 0;
	for (int a = 0; a < 5; a++)
		solution[a] = 1 + rand() % 7;
	for (int b = 0; b < 5; b++) {
		for (int c = 0; c < 10; c++) {
			choice[b][c] = 0;
			verifier[b][c] = false;
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
		case red:
			glColor4d(1.00,0.00,0.00,1.00);
			break;
		case blue:	
			glColor4d(0.00,0.00,1.00,1.00);	
			break;
		case green:	
			glColor4d(0.00,1.00,0.00,1.00);	
			break;
		case yellow:
			glColor4d(1.00,1.00,0.00,1.00);
			break;
		case violet:
			glColor4d(1.00,0.00,1.00,1.00); 
			break;
		case orange:
			glColor4d(1.00,0.50,0.25,1.00);	
			break;
		case brown:	
			glColor4d(0.50,0.25,0.00,1.00);	
			break;
	}
	
}
void showCode () {
	for (int a = 0; a < 5; a++) {	
		chooseColor(solution[a]);
		drawSphere(a/1.5 - 2.5, 2.777, -1.926, 10, 10, 0.2);
	}
	
}
void showVerification(){
	for (int a = 0; a < 5; a++) {
		for (int b = 0; b < 10; b++) {
			if (verifier[a][b]) {
				glColor4d (0,0,0,0);
				drawSphere(a/3.1 + 1.2, b/1.99 - 2.75, -(b/1.99 - 2.75)/3 - 1, 10, 10, 0.1);
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


using namespace std;

void isEnd() {
	for (int j = 0; j < 5; j++) {
		if (!verifier[j][r-1]) break;
		if (j == 4) win = true;
	}
	if (!win) {
		if (choice[4][9] != 0)
			fail = true;
	}
}


void showKeys() {
	glColor4d(1, 1, 1, 1);
	drawText(120, -90, "'r' restart");
	drawText(300, -90, "'1' = red");
	drawText(430, -90, "'2' = blue");
	drawText(560, -90, "'3' = green");
	drawText(690, -90, "'4' = yellow");
	drawText(300, -60, "'5' = violet");
	drawText(430, -60, "'6' = orange");
	drawText(560, -60, "'7' = brown");
}