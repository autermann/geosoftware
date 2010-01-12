#include "main.h"

float arrow_x = -3.5;
float arrow_y;
float arrow_z;
int solution[5];
int choice[5][10];
int s = 0;
int r = 0;
int frame= 0;
bool movement = true;
bool verifier[5][10];
bool win = false;
bool fail = false;
Vector	eye;

void menu(int i){
	switch (i) {
		case 10: reset();						break;
		case 11: exit(0);						break;
		default: if (i > 0 && i < 8) choose(i);	break;
	}
	display();
}

int main(int argc, char** argv) {
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_RGBA | GLUT_DOUBLE | GLUT_DEPTH);
    glutInitWindowSize(800, 600);
    glutCreateWindow("MindMaster");
	glEnable(GL_LINE_SMOOTH);
	glEnable(GL_DEPTH_TEST);
	glDepthMask(GL_TRUE);
    glDepthFunc(GL_LESS);
	glShadeModel(GL_SMOOTH);
	glClearColor(0, 0, 0, 1);
	glutDisplayFunc(display);
    glutReshapeFunc(reshape);
	glutIdleFunc(idle);
    glutKeyboardFunc(key);
	int submenu = glutCreateMenu(menu);
	glutAddMenuEntry("Red\t\t\t1", red);
	glutAddMenuEntry("Blue\t\t2", blue);
	glutAddMenuEntry("Green\t\t3", green);
	glutAddMenuEntry("Yellow\t\t4", yellow);
	glutAddMenuEntry("Violet\t\t5", violet);
	glutAddMenuEntry("Orange\t\t6", orange);
	glutAddMenuEntry("Brown\t\t7", brown);
	glutCreateMenu(menu);
	glutAddMenuEntry("Restart\t\tR", 10);
	glutAddMenuEntry("Exit\t\t\tESC", 11);	
	glutAddSubMenu("Colors", submenu);
	glutAttachMenu(GLUT_RIGHT_BUTTON);
	srand(time(0));
	reset();
	glutMainLoop();
}

int getColorFromKeyCode(int key){
	switch (key) {
		case '1': return red;
		case '2': return blue;
		case '3': return green;
		case '4': return yellow;
		case '5': return violet;
		case '6': return orange;
		case '7': return brown;
		default:  return -1;
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
				drawSphere(a/1.5 - 2.5, b/1.99 - 2.75, -(b/1.99 - 2.75)/3 - 1, 0.2);
		}
	}
}

void chooseColor(int c) {
	switch (c){
		case red:	 glColor4d(1.00f, 0.00f, 0.00f, 1.00f); break;
		case blue:	 glColor4d(0.00f, 0.00f, 1.00f, 1.00f); break;
		case green:	 glColor4d(0.00f, 1.00f, 0.00f, 1.00f); break;
		case yellow: glColor4d(1.00f, 1.00f, 0.00f, 1.00f); break;
		case violet: glColor4d(1.00f, 0.00f, 1.00f, 1.00f); break;
		case orange: glColor4d(1.00f, 0.50f, 0.25f, 1.00f); break;
		case brown:	 glColor4d(0.50f, 0.25f, 0.00f, 1.00f); break;
	}
	
}

void showCode () {
	for (int a = 0; a < 5; a++) {	
		chooseColor(solution[a]);
		drawSphere(a/1.5 - 2.5, 2.777, -1.926, 0.2);
	}
	
}

void showVerification(){
	for (int a = 0; a < 5; a++) {
		for (int b = 0; b < 10; b++) {
			if (verifier[a][b]) {
				glColor4d (0,0,0,0);
				drawSphere(a/3.1 + 1.2, b/1.99 - 2.75, -(b/1.99 - 2.75)/3 - 1, 0.1);
			}
		}
	}
}

void showArrow() {
	if (movement) arrow_x -= 0.01;
	if (movement == false) arrow_x += 0.01;
	if (arrow_x <= -4) movement = false;
	if (arrow_x >= -3.5) movement = true;
	if (r < 10) {
		arrow_y = r/1.99 - 2.75;
		arrow_z = -arrow_y/3 - 1;
	}
	drawArrow(arrow_x, arrow_y, arrow_z);
}

void idle(){
	++frame %= 300;
	if (frame == 0) display();
}

void reshape(int width, int height) {
	glViewport(0, 0, width, height); 
	glMatrixMode(GL_PROJECTION); 
	glLoadIdentity();
	gluPerspective(45, (float)width / (float)height, 1, 100);
    display();
}

void display() {
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
	gluLookAt(9 * sin(eye.x), eye.y,  9 * cos(eye.z), 0, 0, 0, 0, 1, 0);
	showField();
	showChoice();
	if (win || fail) showCode();
	showVerification();
	showArrow();
	glutSwapBuffers();
}

void key(unsigned char k,int,int) {
	switch (k) {
		case 27:  exit(0);							break;
		case 'd': eye.x += 0.02; eye.z = eye.x;		break;
		case 'a': eye.x -= 0.02; eye.z = eye.x;		break;
		case 'w': if (eye.y < 10) eye.y += 0.2;		break;
		case 's': if (eye.y > -10) eye.y -= 0.2;	break;
		case ' ': eye.x = eye.y = eye.z = 0;		break;
		case 'r': reset();							break;
		default:
			if (!win && !fail){
				choose(getColorFromKeyCode(k));
				isEnd();
			}										break;
	}
	display();
}

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

void choose(int color) {
	if (color != -1){
		choice[s][r] = color;
		if (choice[s][r] == solution[s])
			verifier[s][r] = true;
		if ((++s %= 5) == 0) r++;
	}
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

void drawCube(double x, double y, double z, double size) {
	size /= 2;
	glPushMatrix();
	glBegin (GL_QUADS); 	
	glVertex3f (x - size, y - size, z + size); 
	glVertex3f (x - size, y + size, z + size); 
	glVertex3f (x + size, y + size, z + size); 
	glVertex3f (x + size, y - size, z + size);
	glVertex3f (x - size, y - size, z - size); 
	glVertex3f (x - size, y + size, z - size); 
	glVertex3f (x + size, y + size, z - size); 
	glVertex3f (x + size, y - size, z - size); 
	glVertex3f (x - size, y + size, z - size); 
	glVertex3f (x + size, y + size, z - size); 
	glVertex3f (x + size, y + size, z + size); 
	glVertex3f (x - size, y + size, z + size); 
	glVertex3f (x - size, y - size, z - size);
	glVertex3f (x + size, y - size, z - size); 
	glVertex3f (x + size, y - size, z + size); 
	glVertex3f (x - size, y - size, z + size); 
	glVertex3f (x - size, y - size, z - size); 
	glVertex3f (x - size, y + size, z - size); 
	glVertex3f (x - size, y + size, z + size); 
	glVertex3f (x - size, y - size, z + size); 
	glVertex3f (x + size, y - size, z - size); 
	glVertex3f (x + size, y + size, z - size); 
	glVertex3f (x + size, y + size, z + size); 
	glVertex3f (x + size, y - size, z + size); 
	glEnd();
	glPopMatrix();
}

void drawSphere(double x, double y, double z, double radius) {
	double p = 10,  m = 10, DOUBLE_PI = 2 * PI, Theta, Theta2, Phi, Vx, Vy, Vz;
	glPushMatrix ();
	for (int i = 0; i < p; i++) {
		glBegin(GL_TRIANGLE_STRIP);
		Theta  = i * DOUBLE_PI / p;
		Theta2 = (i + 1) * DOUBLE_PI / p;
		for	(int j = 0; j < p; j++) {
			Phi  = j * DOUBLE_PI / m;
			Vx = cos(Theta) * cos(Phi);
			Vy = cos(Theta) * sin(Phi);
			Vz = sin(Theta);
			glNormal3f(Vx, Vy, Vz );
			glVertex3d(x + radius * Vx, y + radius * Vy, z + radius * Vz );
			Vx = cos(Theta2) * cos(Phi);
			Vy = cos(Theta2) * sin(Phi);
			Vz = sin(Theta2);
			glNormal3f(Vx, Vy, Vz);
			glVertex3d(x + radius * Vx, y + radius * Vy, z + radius * Vz );
		}
		glEnd();
	}
	glPopMatrix ();
}

void drawArrow(float x, float y, float z) {
	float l = 0.15f, h = 0.15f, p = 0.45f;
	glPushMatrix();
	glColor4d(1, 0, 0, 1);
	glBegin(GL_QUADS);
	glVertex3f(x, y - l, z - h); 
	glVertex3f(x, y + l, z - h); 
	glVertex3f(x, y + l, z + h); 
	glVertex3f(x, y - l, z + h); 
	glEnd();
	glBegin(GL_TRIANGLES);
	glVertex3f(x, y - l, z + h); 
	glVertex3f(x, y + l, z + h); 
	glVertex3f(x + p, y, z); 
	glEnd();
	glBegin(GL_TRIANGLES);
	glVertex3f(x, y - l, z - h); 
	glVertex3f(x, y + l, z - h); 
	glVertex3f(x + p, y, z); 
	glEnd();
	glBegin(GL_TRIANGLES);
	glVertex3f(x, y + l, z + h);
	glVertex3f(x, y + l, z - h); 
	glVertex3f(x + p, y, z); 
	glEnd();
	glBegin(GL_TRIANGLES);
	glVertex3f(x, y - l, z + h); 
	glVertex3f(x, y - l, z - h); 
	glVertex3f(x + p, y, z); 
	glEnd();
	glBegin(GL_QUADS);
	glVertex3f(x, y + l/2, z + l/2); 
	glVertex3f(x - p * 1.5, y + l/2, z + l/2); 
	glVertex3f(x - p * 1.5, y + l/2, z - l/2); 
	glVertex3f(x, y + l/2, z - l/2); 
	glEnd();
	glBegin(GL_QUADS);
	glVertex3f(x, y - h/2, z + l/2); 
	glVertex3f(x - p * 1.5, y - h/2, z + l/2); 
	glVertex3f(x - p * 1.5, y - h/2, z - l/2); 
	glVertex3f(x, y - h/2, z - l/2); 
	glEnd();
	glBegin (GL_QUADS);
	glVertex3f(x, y + h/2, z - l/2); 
	glVertex3f(x - p * 1.5, y + h/2, z - l/2); 
	glVertex3f(x - p * 1.5, y - h/2, z - l/2); 
	glVertex3f(x, y - h/2, z - l/2); 
	glEnd();
	glBegin(GL_QUADS);
	glVertex3f(x, y + h/2, z + l/2); 
	glVertex3f(x - p * 1.5, y + h/2, z + l/2); 
	glVertex3f(x - p * 1.5, y - h/2, z + l/2); 
	glVertex3f(x, y - h/2, z + l/2); 
	glEnd();
	glBegin(GL_QUADS);
	glVertex3f(x - p * 1.5, y - h/2, z - l/2 ); 
	glVertex3f(x - p * 1.5, y - h/2, z + l/2 ); 
	glVertex3f(x - p * 1.5, y + h/2, z + l/2 ); 
	glVertex3f(x - p * 1.5, y + h/2, z - l/2 ); 
	glEnd();
	glPopMatrix();
}