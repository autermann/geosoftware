#ifndef PRIMITIVES_H
#define PRIMITIVES_H

void drawText(int x, int y, char *str){
	glRasterPos3i(x, y, 0);
	char *c;
    for (c = str; *c != '\0'; c++)
        glutBitmapCharacter(GLUT_BITMAP_HELVETICA_18, *c);
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
void drawSphere(double x, double y, double z, double paralleles, double meridiens, double radius) {
	double DOUBLE_PI = 2 * PI, Theta, Theta2, Phi, Vx, Vy, Vz;
	glPushMatrix ();
	for (int i = 0; i < paralleles; i++) {
		glBegin(GL_TRIANGLE_STRIP);
		Theta  = i * DOUBLE_PI / paralleles;
		Theta2 = (i + 1) * DOUBLE_PI / paralleles;
		for (int j = 0; j < paralleles; j++) {
			Phi  = j * DOUBLE_PI / meridiens;
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

#endif