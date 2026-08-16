#include <GL/glut.h>
#include <stdlib.h>
#include <stdio.h>
#include <math.h>

bool light0_on = false, light1_on = false, light2_on = false, light3_on = false;

double windowHeight = 800, windowWidth = 600;
double eyeX = 15.0, eyeY = 0.0, eyeZ = 30.0, refX = 0, refY = 0, refZ = 0;

void Lighting(){
	glEnable(GL_DEPTH_TEST);
    glEnable(GL_NORMALIZE);
    glEnable(GL_COLOR_MATERIAL);
    glEnable(GL_LIGHT0);
    light0_on = true;
	
	// Setup LIGHT0
    GLfloat light_pos[] = { 0.0f, 4.6f, 0.0f, 1.0f };
    GLfloat light_amb[] = { 0.2f, 0.2f, 0.2f, 1.0f };
    GLfloat light_diff[] = { 0.8f, 0.8f, 0.8f, 1.0f };
    GLfloat light_spec[] = { 1.0f, 1.0f, 1.0f, 1.0f };

    glLightfv(GL_LIGHT0, GL_POSITION, light_pos);
    glLightfv(GL_LIGHT0, GL_AMBIENT, light_amb);
    glLightfv(GL_LIGHT0, GL_DIFFUSE, light_diff);
    glLightfv(GL_LIGHT0, GL_SPECULAR, light_spec);
    
    GLfloat material_shininess[] = { 100.0 };
	glMaterialfv(GL_FRONT, GL_SHININESS, material_shininess);
}

void ReShape(int width, int height) {
    glViewport(0, 0, width, height);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    float ratio = (float)width / (float)height;
    gluPerspective(45.0, ratio, 1, 100.0);
    glMatrixMode(GL_MODELVIEW);
}

void TrucToaDo() {
    //glDisable(GL_LIGHTING);
    glLineWidth(1.0);
    glBegin(GL_LINES);
    glColor3f(1.0, 0.0, 0.0);
    glVertex3f(0.0, 0.0, 0.0);
    glVertex3f(10.0, 0.0, 0.0);
    glEnd();

    glBegin(GL_LINES);
    glColor3f(0.0, 1.0, 0.0);
    glVertex3f(0.0, 0.0, 0.0);
    glVertex3f(0.0, 10.0, 0.0);
    glEnd();

    glBegin(GL_LINES);
    glColor3f(0.0, 0.0, 1.0);
    glVertex3f(0.0, 0.0, 0.0);
    glVertex3f(0.0, 0.0, 10.0);
    glEnd();
    //glEnable(GL_LIGHTING);
}
/*
void setWoodMaterial() {
    GLfloat ambient[] = { 0.2f, 0.1f, 0.0f, 1.0f }; // Màu ambient (ánh sáng xung quanh)
    GLfloat diffuse[] = { 0.6f, 0.3f, 0.0f, 1.0f }; // Màu diffuse (màu c?a v?t li?u khi chi?u sáng)
    GLfloat specular[] = { 0.4f, 0.2f, 0.0f, 1.0f }; // Màu specular (màu c?a ?i?m sáng ph?n x?)
    GLfloat shininess = 32.0f; // ?? bóng c?a v?t li?u

    glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT, ambient); // Thi?t l?p màu ambient
    glMaterialfv(GL_FRONT_AND_BACK, GL_DIFFUSE, diffuse); // Thi?t l?p màu diffuse
    glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, specular); // Thi?t l?p màu specular
    glMaterialf(GL_FRONT_AND_BACK, GL_SHININESS, shininess); // Thi?t l?p ?? bóng
}*/

// ------------------------------ //

void book(float angle, float width, float height, float length, float x, float y, float z, float r, float g, float b){
	glColor3f(r, g, b);
    glPushMatrix();
    
    glTranslatef(x, y, z);
    glRotatef(angle, 0.0, 0.0, 1.0);
    glScalef(width, height, length);
    glutSolidCube(0.2);
    
    glPopMatrix();
}

void drawBook(){
	// Ke sach 1
    book(5.0, 1.0,4.0,2.5, -4.7,-1.5,-9.2,  1,0,0);
    book(5.0, 1.0,4.0,2.5, -4.4,-1.5,-9.2,  1,0,0);
    book(5.0, 1.0,4.0,2.5, -4.1,-1.5,-9.2,  1,0,0);
    book(5.0, 1.0,4.0,2.5, -3.8,-1.5,-9.2,  1,0,0);
    
    // Ke sach 2
	book(0.0, 1.0,4.0,2.5, -4.7,-0.5,-9.2,  0,1,0);
    book(0.0, 1.0,4.0,2.5, -4.45,-0.5,-9.2,  0,1,0);
    book(0.0, 1.0,4.0,2.5, -4.2,-0.5,-9.2,  0,1,0);
    book(0.0, 1.0,4.0,2.5, -3.95,-0.5,-9.2,  0,1,0);
    book(0.0, 1.0,4.0,2.5, -3.7,-0.5,-9.2,  0,1,0);
    
    // Ke sach 3
    book(-10.0, 2.0,4.0,2.5, 4.7,-1.5,-9.2,  1,1,0);
    book(-10.0, 2.0,4.0,2.5, 4.2,-1.5,-9.2,  0,1,1);
    book(-10.0, 2.0,4.0,2.5, 3.7,-1.5,-9.2,  1,0,1);
    
    // Ke sach 4
    book(5.0, 1.0,4.0,2.5, -7.7,-1.5,-9.2,  1.0,0.5,1.0);
    book(5.0, 1.0,4.0,2.5, -7.4,-1.5,-9.2,  0.5,1.0,1.0);
    book(5.0, 2.0,4.0,2.5, -7.0,-1.5,-9.2,  0.5,1.0,0.5);
    book(5.0, 1.0,4.0,2.5, -6.6,-1.5,-9.2,  0.0,1.0,1.0);
    book(30.0, 1.0,4.0,2.5, -6.2,-1.5,-9.2,  1.0,1.0,0.0);
    book(0.0, 4.0,2.5,2.5, -5.5,-1.7,-9.2,  0.3,0.3,0.3);
}

void BookPen(){
	// Bàn ph?i 
	glPushMatrix();
	glTranslatef(8.0f, -1.85f, -5.5f);
    glScalef(1.5, 1.5, 1.5);
    
	book(90.0, 0.5,4.0,2.5, 0,0,0,  1,1,1);
	
	glPushMatrix();
    glTranslatef(0.0, 0.0, 0.3);
	glColor3f(0.3, 0.3, 0.3);
	glScalef(5.5f, 0.5f, 0.5f);
	glutSolidCube(0.1f);
    glPopMatrix();
    
	glPopMatrix();
	
	// Bàn gi?a 
	glPushMatrix();
	glTranslatef(1.0f, -1.95f, -7.5f);
    glScalef(1.5, 1.5, 1.5);
    glRotatef(90.0, 0.0, 1.0, 0.0);
    
	book(90.0, 0.5,4.0,2.5, 0,0,0,  1,1,1);
	
	glPushMatrix();
    glTranslatef(0.0, 0.0, 0.3);
	glColor3f(0.3, 0.3, 0.3);
	glScalef(5.5f, 0.5f, 0.5f);
	glutSolidCube(0.1f);
    glPopMatrix();
    
	glPopMatrix();
}

void Bottle() {
	glPushMatrix();
    glTranslatef(-3.0, 1.5, -9.2);
    
    // Thân chai
    glColor3f(0.5, 1.0, 0.5); 
    glPushMatrix();
    glTranslatef(0.0, 0.0, 0.0);
    glScalef(0.4, 0.8, 0.4);
    glutSolidCube(1.0);
    glPopMatrix();

    // C? chai
    glColor3f(0.5, 1.0, 0.5);
    glPushMatrix();
    glTranslatef(0.0, 0.6, 0.0);
    glScalef(0.2, 0.4, 0.2);
    glutSolidCube(1.0);
    glPopMatrix();

    // ??nh chai
    glColor3f(1.0, 0.5, 0.5);
    glPushMatrix();
    glTranslatef(0.0, 0.85, 0.0);
    glScalef(0.3, 0.1, 0.3);
    glutSolidCube(1.0);
    glPopMatrix();
    
    glPopMatrix();
}

void TeaPot(){
	glPushMatrix();
	glTranslatef(-2.0, 1.4, -9.2);
    glColor3f(1.0, 1.0, 0.0); // ??t màu cho ?m trà 
    glutSolidTeapot(0.5); // V? ?m trà
    glPopMatrix();
}

void Pyramid() {
	glPushMatrix();
	glTranslatef(-0.5, 1.55, -9.2);
	glScalef(0.5, 0.5, 0.5);
	glRotatef(30.0,0,1,0);
	
    glBegin(GL_TRIANGLES);
    // M?t tr??c
    glColor3f(0.5, 1.0, 1.0);
    glVertex3f(0.0, 1.0, 0.0); // ??nh
    glVertex3f(-1.0, -1.0, 1.0); // Góc d??i bên trái
    glVertex3f(1.0, -1.0, 1.0); // Góc d??i bên ph?i

    // M?t ph?i
    glColor3f(0.0, 1.0, 0.0); // Xanh lá
    glVertex3f(0.0, 1.0, 0.0); // ??nh
    glVertex3f(1.0, -1.0, 1.0); // Góc d??i bên ph?i
    glVertex3f(1.0, -1.0, -1.0); // Góc d??i bên ph?i

    // M?t sau
    glColor3f(0.0, 0.0, 1.0); // Xanh d??ng
    glVertex3f(0.0, 1.0, 0.0); // ??nh
    glVertex3f(1.0, -1.0, -1.0); // Góc d??i bên ph?i
    glVertex3f(-1.0, -1.0, -1.0); // Góc d??i bên trái

    // M?t trái
    glColor3f(0.5, 0.5, 0.0);
    glVertex3f(0.0, 1.0, 0.0); // ??nh
    glVertex3f(-1.0, -1.0, -1.0); // Góc d??i bên trái
    glVertex3f(-1.0, -1.0, 1.0); // Góc d??i bên trái

    // M?t d??i
    glColor3f(1.0, 0.0, 1.0); // Màu h?ng
    glVertex3f(-1.0, -1.0, 1.0); // Góc d??i bên trái
    glVertex3f(1.0, -1.0, 1.0); // Góc d??i bên ph?i
    glVertex3f(1.0, -1.0, -1.0); // Góc d??i bên ph?i

    glVertex3f(-1.0, -1.0, 1.0); // Góc d??i bên trái
    glVertex3f(-1.0, -1.0, -1.0); // Góc d??i bên trái
    glVertex3f(1.0, -1.0, -1.0); // Góc d??i bên ph?i

    glEnd();
    
    glPopMatrix();
}

void Ball() {
    glPushMatrix();
    glColor3f(0.3, 0.0, 0.2);
    glTranslatef(0.7, 1.5, -9.2);
    glRotatef(45,0,1,0);
    glutWireSphere(0.5, 20, 15);
    glPopMatrix();
}

void Trophy() {
	glPushMatrix();
	glTranslatef(5.0, -0.7, -9.0);
    glScalef(1.5, 1.5, 1.5);
	
	// ?? cúp 
    glColor3f(0.3, 0.3, 0.3); 
    glPushMatrix();
    glTranslatef(0.0, 0.0, 0.0);
    glScalef(0.5, 0.1, 0.5);
    glutSolidCube(1.0);
    glPopMatrix();
	
	glColor3f(0.3, 0.3, 0.3); 
    glPushMatrix();
    glTranslatef(0.0, 0.2, 0.0);
    glScalef(0.4, 0.3, 0.4);
    glutSolidCube(1.0);
    glPopMatrix();
    
    // Thân cúp 
    glPushMatrix();
    GLUquadric* quad = gluNewQuadric();
    
	glTranslatef(0.0, 0.35, 0.0);
	glColor3f(1.0, 1.0, 0.0);
    glPushMatrix(); 
    glRotatef(-90, 1.0, 0.0, 0.0);
    gluCylinder(quad, 0.15, 0.1, 0.4, 32, 32);
    glPopMatrix();
    
	glTranslatef(0.0, 0.4, 0.0);
	glColor3f(1.0, 1.0, 0.0);
    glPushMatrix(); 
    glRotatef(-90, 1.0, 0.0, 0.0);
    gluCylinder(quad, 0.1, 0.15, 0.4, 32, 32);
    glPopMatrix();
    
    gluDeleteQuadric(quad);
    glPopMatrix();
	
	// ??nh cúp
	glPushMatrix();
    glColor3f(1.0, 1.0, 0.0);
    glTranslatef(0.0, 1.4, 0.0);
    glRotatef(45,0,1,0);
    glutSolidSphere(0.35, 20, 15);
    glPopMatrix();
    
    glPopMatrix();
}

void Lamp() {
	// Bàn ph?i 
	glPushMatrix();
	glTranslatef(8.5f, -1.85f, -3.5f);
	
    // Chân dèn 
    glPushMatrix();
    glTranslatef(0.0, 0.0, 0.0);
    glColor3f(0.8, 0.8, 0.8);
	glScalef(1.0f, 0.1f, 1.0f);
	glutSolidCube(0.5f);
    glPopMatrix();
    
    // Thân dèn 
    glPushMatrix();
    glTranslatef(0.0, 0.2, 0.0);
    glColor3f(0.8, 0.8, 0.8);
	glScalef(0.5f, 4.5f, 0.5f);
	glutSolidCube(0.1f);
    glPopMatrix();
    
    // ?èn
    glColor3f(1.0, 0.5, 0.5);
	GLUquadric* quad = gluNewQuadric();
    glPushMatrix();
    glTranslatef(0.0f, 0.4f, 0.0f); 
    glRotatef(-90, 1.0, 0.0, 0.0);
    gluCylinder(quad, 0.4, 0.3, 0.6, 32, 32);
    glPopMatrix();
    
    glPopMatrix();
    
    
    // Bàn gi?a 
    glPushMatrix();
	glTranslatef(-3.0f, -1.95f, -8.0f);
    // Chân dèn 
    glPushMatrix();
    glTranslatef(0.0, 0.0, 0.0);
    glColor3f(0.8, 0.8, 0.8);
	glScalef(1.0f, 0.1f, 1.0f);
	glutSolidCube(0.5f);
    glPopMatrix();
    
    // Thân dèn 
    glPushMatrix();
    glTranslatef(0.0, 0.2, 0.0);
    glColor3f(0.8, 0.8, 0.8);
	glScalef(0.5f, 4.5f, 0.5f);
	glutSolidCube(0.1f);
    glPopMatrix();
    
    // ?èn
    glColor3f(0.4, 1.0, 1.0);
    glPushMatrix();
    glTranslatef(0.0f, 0.4f, 0.0f); 
    glRotatef(-90, 1.0, 0.0, 0.0);
    gluCylinder(quad, 0.4, 0.3, 0.6, 32, 32);
    glPopMatrix();
    gluDeleteQuadric(quad);
    
    glPopMatrix();
}

void CeilingLights(){
	glPushMatrix();
	glTranslatef(0.0, 4.6, 0.0);
	glColor3f(1.0, 1.0, 1.0); // Màu tr?ng
    glutSolidSphere(0.5, 50, 50);
    glPopMatrix();
}

void Bin() {
	glPushMatrix();
	glTranslatef(8.5f, -4.8f, -0.8f);
	
	glColor3f(0.3, 0.3, 0.5);
	GLUquadric* quad = gluNewQuadric();
    glPushMatrix(); 
    glRotatef(-90, 1.0, 0.0, 0.0);
    gluCylinder(quad, 0.6, 0.8, 1.5, 32, 32);
    glPopMatrix();
    gluDeleteQuadric(quad);
    
    glPopMatrix();
}

void drawCircle(float radius, int numPoints) {
    glBegin(GL_TRIANGLE_FAN);
    int i;
    for (i = 0; i < numPoints; ++i) {
        float angle = 2.0f * 3.14159265359f * i / numPoints;
        float x = radius * cos(angle);
        float y = radius * sin(angle);
        glVertex2f(x, y);
    }
    glEnd();
}

void Carpet(){
	glPushMatrix();
	glTranslatef(1.0f, -4.88f, 3.0f);
    glScalef(10.0, 10.0, 10.0);
    glRotatef(-90, 1.0, 0.0, 0.0);
    
    // Pink carpet
    glPushMatrix();
	glTranslatef(0.0f, 0.0f, 0.0f);
    glColor3f(1.0f, 0.5f, 0.7f);
    drawCircle(0.6f, 100);
	glPopMatrix();
	
	glPushMatrix();
	glTranslatef(0.0f, 0.0f, -0.001f);
    glColor3f(0.0f, 0.0f, 0.0f);
    drawCircle(0.63f, 100);
	glPopMatrix();
	
	// Bear

	glPushMatrix();
	glColor3f(0.0f, 0.0f, 0.f);
	glTranslatef(0.0f, -0.1f, 0.0f);
	glPopMatrix();	
	// Ears
	// Ears-R
	glPushMatrix();
	glTranslatef(0.35f, 0.25f, 0.001f);
    glColor3f(0.3f, 0.3f, 0.3f);
    drawCircle(0.15f, 100);
	glPopMatrix();
	
	glPushMatrix();
	glTranslatef(0.35f, 0.25f, 0.0011f);
    glColor3f(0.7f, 0.4f, 0.1f);
    drawCircle(0.13f, 100);
	glPopMatrix();
	
	// Ears-L
	glPushMatrix();
	glTranslatef(-0.35f, 0.25f, 0.001f);
    glColor3f(0.3f, 0.3f, 0.3f);
    drawCircle(0.15f, 100);
	glPopMatrix();
	
	glPushMatrix();
	glTranslatef(-0.35f, 0.25f, 0.0011f);
    glColor3f(0.7f, 0.4f, 0.1f);
    drawCircle(0.13f, 100);
	glPopMatrix();
	
    // Face 
	glPushMatrix();
	glTranslatef(0.0f, 0.0f, 0.00111f);
    glColor3f(0.3f, 0.3f, 0.3f);
    drawCircle(0.4f, 100);
	glPopMatrix();
	
	glPushMatrix();
	glTranslatef(0.0f, 0.0f, 0.00112f);
    glColor3f(0.7f, 0.4f, 0.1f);
    drawCircle(0.38f, 100);
	glPopMatrix();
	
	// Nose
	glPushMatrix();
	glTranslatef(0.0f, -0.1f, 0.0021f);
    glColor3f(0.0f, 0.0f, 0.0f);
    drawCircle(0.05f, 100);
	glPopMatrix();

	glPushMatrix();
	glTranslatef(0.0f, -0.15f, 0.002f);
    glColor3f(1.0f, 1.0f, 1.0f);
    drawCircle(0.2f, 100);
	glPopMatrix();
	
	// Eyes
	// Eye-R
	glPushMatrix();
	glTranslatef(0.2f, 0.15f, 0.0021f);
    glColor3f(0.0f, 0.0f, 0.0f);
    drawCircle(0.085f, 100);
	glPopMatrix();
	
	glPushMatrix();
	glTranslatef(0.2f, 0.15f, 0.002f);
    glColor3f(1.0f, 1.0f, 1.0f);
    drawCircle(0.1f, 100);
	glPopMatrix();
	
	// Eye-L
	glPushMatrix();
	glTranslatef(-0.2f, 0.15f, 0.0021f);
    glColor3f(0.0f, 0.0f, 0.0f);
    drawCircle(0.085f, 100);
	glPopMatrix();
	
	glPushMatrix();
	glTranslatef(-0.2f, 0.15f, 0.002f);
    glColor3f(1.0f, 1.0f, 1.0f);
    drawCircle(0.1f, 100);
	glPopMatrix();
	
	glPopMatrix();
	
    glPopMatrix();
} 

void Carpet1(){
    glPushMatrix();
    glTranslatef(-8.5, -4.9, 5.5);
    glScalef(2.0, 0.01, 4.0);
    glColor3f(0.5, 1.0, 0.5); 
    glutSolidCube(1.0);
    glPopMatrix();
} 

void PlantPot() {
	glPushMatrix();
	glTranslatef(8.5f, 0.0f, -9.2f);
	
	//Pot
	glColor3f(0.5, 0.5, 1.0);
	GLUquadric* quad = gluNewQuadric();
    glPushMatrix();
    glTranslatef(0.0f, -1.0f, 0.0f); 
    glRotatef(-90, 1.0, 0.0, 0.0);
    gluCylinder(quad, 0.3, 0.4, 0.5, 32, 32);
    glPopMatrix();
    gluDeleteQuadric(quad);
	
	//Plant
	// Than
    glColor3f(0.5, 0.3, 0.0);
    glPushMatrix();
    glTranslatef(0.0f, -0.5f, 0.0f);
    glScalef(0.05, 0.8, 0.05);
    glutSolidCube(1.0);
    glPopMatrix();
    
	//La
    glColor3f(0.0, 0.5, 0.0);
    glPushMatrix();
    glTranslatef(0.0f, 0.5f, 0.0f);
    glutSolidCone(0.7, 0.7, 6, 1);
    glPopMatrix();

    glPushMatrix();
    glTranslatef(0.0f, 1.2f, 0.0f);
    glutSolidCone(0.5, 0.5, 6, 1);
    glPopMatrix();

    glPushMatrix();
    glTranslatef(0.0f, 1.75f, 0.0f);
    glutSolidCone(0.3, 0.3, 6, 1);
    glPopMatrix();
    
    glPopMatrix();
}

void VietNam(){
	glPushMatrix();
	glTranslatef(-1.0, 0.0, -9.6);
	
	// Frag
	glColor3f(1.0, 0.0, 0.0);
    glPushMatrix();
    glTranslatef(0.0, -0.5, 0.0);
    glScalef(18.0, 12.0, 1.0);
    glutSolidCube(0.2);
    glPopMatrix();
    
    // Star
    glPushMatrix();
    glTranslatef(2.0, -2.5, 0.2);
    glScalef(3.0, 3.0, 3.0); // G?p ?ôi t? l?
    glBegin(GL_TRIANGLES);
	glColor3f(1, 1, 0);
	glVertex3f(-0.60, 0.77, 0);
	glVertex3f(-0.42, 0.77, 0);
	glVertex3f(-0.58, 0.68, 0);
	
	// Second triangle top triangle
	glVertex3f(-0.64, 1, 0);
	glVertex3f(-0.68, 0.77, 0);
	glVertex3f(-0.60, 0.77, 0);
	
	// 3rd Triangle
	glVertex3f(-0.68, 0.77, 0);
	glVertex3f(-0.7, 0.68, 0);
	glVertex3f(-0.86, 0.77, 0);
	
	// 4th Triangle
	glVertex3f(-0.64, 0.63, 0);
	glVertex3f(-0.7, 0.68, 0);
	glVertex3f(-0.82, 0.43, 0);
	
	// 5th Triangle
	glVertex3f(-0.64, 0.63, 0);
	glVertex3f(-0.58, 0.68, 0);
	glVertex3f(-0.51, 0.43, 0);
	
	glEnd();
	
	// The Polygon within the stars
	glBegin(GL_POLYGON);
	glColor3f(1, 1, 0);
	
	glVertex3f(-0.68, 0.77, 0);
	glVertex3f(-0.60, 0.77, 0);
	glVertex3f(-0.7, 0.68, 0);
	glVertex3f(-0.64, 0.63, 0);
	glVertex3f(-0.58, 0.68, 0);
	
	glEnd();
	
	glBegin(GL_POLYGON);
	glColor3f(1, 1, 0);
	
	glVertex3f(-0.60, 0.77, 0);
	glVertex3f(-0.68, 0.77, 0);
	glVertex3f(-0.7, 0.68, 0);
	glVertex3f(-0.64, 0.63, 0);
	glVertex3f(-0.58, 0.68, 0);
	
	glEnd();
	glPopMatrix();
	
	glPopMatrix();
}

void PictureFrame() {
	glPushMatrix();
	glTranslatef(-8.0, 1.0, -9.8);
	
    // Khung tranh
    glColor3f(0.8f, 0.6f, 0.4f);
    glBegin(GL_QUADS);
    glVertex3f(-1.0f, -1.0f, 0.0f);
    glVertex3f(1.0f, -1.0f, 0.0f);
    glVertex3f(1.0f, 1.0f, 0.0f);
    glVertex3f(-1.0f, 1.0f, 0.0f);
    glEnd();

    // B?c tranh
    glColor3f(0.0f, 0.0f, 0.0f); // Màu ?en
    glBegin(GL_QUADS);
    glVertex3f(-0.9f, -0.9f, 0.01f);
    glVertex3f(0.9f, -0.9f, 0.01f);
    glVertex3f(0.9f, 0.9f, 0.01f);
    glVertex3f(-0.9f, 0.9f, 0.01f);
    glEnd();
	
    // Chi ti?t trên b?c tranh
    glColor3f(1.0, 1.0, 0.0);
	glTranslatef(0.0, 0.0, 0.1);
	glLineWidth(2.0);
	const float PI = 3.14159265358979323846;
	const float Radius = 0.6f;
	float starRadius = 0.6f;
	float circleRadius = 0.6f;

	const int numPoints = 100; // S? lu?ng di?m d? v? h?nh tr?n
	const float rotation = PI / 5.0f; // 72 degrees in radians
	float angle = PI / 2.0f;
	float x, y;
	int i;
	
    // V? h?nh tr?n bao quanh ngôi sao
    glBegin(GL_LINE_LOOP);
    for (i = 0; i < numPoints; ++i) {
        x = circleRadius * cos(2 * PI * i / numPoints);
        y = circleRadius * sin(2 * PI * i / numPoints);
        glVertex2f(x, y);
    }
    glEnd();

    glPushMatrix(); // Luu tr?ng thái ma tr?n hi?n t?i
    glBegin(GL_LINE_LOOP);
    for (i = 0; i < 5; ++i) {
        x = starRadius * cos(angle);
        y = starRadius * sin(angle);
        glVertex2f(x, y);
        angle += rotation;

        x = (starRadius / 2.0f) * cos(angle);
        y = (starRadius / 2.0f) * sin(angle);
        glVertex2f(x, y);
        angle += rotation;
    }
    glEnd();
    glPopMatrix();
    
    glPopMatrix();
}

void Other(){
	// Cube
	glPushMatrix();
	glTranslatef(2.3, 0.2, -9.2);
	glRotatef(-30.0, 0.0, 1.0, 0.0);
	
	glColor3f(1.0, 0.7, 0.7);
    glutSolidCube(0.6);
    glPopMatrix();
    
    // Torus
    glPushMatrix();
	glTranslatef(2.0, -0.7, -9.5);
	
    glColor3f(0.0, 0.5, 0.5);
    glutSolidTorus(0.1, 0.2, 32, 32);
    glPopMatrix();
    
    glPushMatrix();
	glTranslatef(2.5, -0.7, -9.5);
	
    glColor3f(0.0, 0.5, 0.5);
    glutSolidTorus(0.1, 0.2, 32, 32);
    glPopMatrix();
    
    // Cone 
    glPushMatrix();
	glTranslatef(-4.2, 1.0, -9.5);
	glRotatef(-90.0, 1.0, 0.0, 0.0);
	
    glColor3f(0.0, 0.5, 0.5);
    glutSolidCone(0.4, 1.0, 20, 20); // ???ng kính ?áy là 1.0, chi?u cao là 2.0
    glPopMatrix();
    
    // Wire Cube
    glPushMatrix();
	glTranslatef(-5.7, 0.4, -9.5);
	glRotatef(-45.0, 1.0, 1.0, 0.0);
	
	glColor3f(0.5, 0.0, 1.0);
    glutWireCube(0.4);
    glPopMatrix();
    
    // Wire Torus
    glPushMatrix();
    glTranslatef(-5.7, 1.5, -9.5);
	
    glColor3f(0.0, 0.5, 0.5);
	glutWireTorus(0.2, 0.25, 10, 10);
    
    glPopMatrix();
}

// ------------------------------ //

void Bookshelf() {
    glColor3f(180.0 / 255.0, 95.0 / 255.0, 4.0 / 255.0); // Màu #B45F04
    glPushMatrix();
    glTranslatef(0.0, -2, -9.2); // Ch?nh v? trí
    glScalef(20, 0.2, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();


    // ve thanh tren cua tu sach
    glColor3f(180.0 / 255.0, 95.0 / 255.0, 4.0 / 255.0); // Màu #B45F04
    glPushMatrix();
    glTranslatef(0.0, 2.5, -9.2); // Ch?nh v? trí
    glScalef(20, 0.2, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();


    // ve thanh doc dai cua tu sach
    glColor3f(180.0 / 255.0, 95.0 / 255.0, 4.0 / 255.0); // Màu #B45F04
    glPushMatrix();
    glTranslatef(-9.99, 0.3, -9.2); // Ch?nh v? trí
    glScalef(0.19, 4.4, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();

    // ve thanh doc dai 2 cua tu sach
    glColor3f(180.0 / 255.0, 95.0 / 255.0, 4.0 / 255.0); // Màu #B45F04
    glPushMatrix();
    glTranslatef(-9.3, 0.3, -9.2); // Ch?nh v? trí
    glScalef(0.13, 4.4, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();

    // ve thanh doc dai 3 cua tu sach
    glColor3f(180.0 / 255.0, 95.0 / 255.0, 4.0 / 255.0); // Màu #B45F04
    glPushMatrix();
    glTranslatef(-5, 0.3, -9.2); // Ch?nh v? trí
    glScalef(0.13, 4.4, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();

    // ve thanh doc dai 4 cua tu sach
    glColor3f(180.0 / 255.0, 95.0 / 255.0, 4.0 / 255.0); // Màu #B45F04
    glPushMatrix();
    glTranslatef(3, 0.3, -9.2); // Ch?nh v? trí
    glScalef(0.13, 4.4, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();

    // ve thanh doc dai 5 cua tu sach
    glColor3f(180.0 / 255.0, 95.0 / 255.0, 4.0 / 255.0); // Màu #B45F04
    glPushMatrix();
    glTranslatef(7, 0.3, -9.2); // Ch?nh v? trí
    glScalef(0.13, 4.4, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();


    // ve thanh doc dai 6 cua tu sach
    glColor3f(180.0 / 255.0, 95.0 / 255.0, 4.0 / 255.0); // Màu #B45F04
    glPushMatrix();
    glTranslatef(9.9, 0.3, -9.2); // Ch?nh v? trí
    glScalef(0.13, 4.4, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();

    // ve thanh doc vua 1 cua tu sach
    glColor3f(180.0 / 255.0, 95.0 / 255.0, 4.0 / 255.0); // Màu #B45F04
    glPushMatrix();
    glTranslatef(-6.5, 0.8, -9.2); // Ch?nh v? trí
    glScalef(0.11, 3.3, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();

    // ve thanh doc vua 2 cua tu sach
    glColor3f(180.0 / 255.0, 95.0 / 255.0, 4.0 / 255.0); // Màu #B45F04
    glPushMatrix();
    glTranslatef(4.0, 0.8, -9.2); // Ch?nh v? trí
    glScalef(0.11, 3.3, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();

    // ve thanh doc vua 3 cua tu sach
    glColor3f(180.0 / 255.0, 95.0 / 255.0, 4.0 / 255.0); // Màu #B45F04
    glPushMatrix();
    glTranslatef(6.0, 0.8, -9.2); // Ch?nh v? trí
    glScalef(0.11, 3.3, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();

    // ve thanh doc ngan 1 cua tu sach
    glColor3f(180.0 / 255.0, 95.0 / 255.0, 4.0 / 255.0); // Màu #B45F04
    glPushMatrix();
    glTranslatef(-8.0, -1.4, -9.2); // Ch?nh v? trí
    glScalef(0.1, 1.3, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();

    // ve thanh doc ngan 2 cua tu sach
    glColor3f(180.0 / 255.0, 95.0 / 255.0, 4.0 / 255.0); // Màu #B45F04
    glPushMatrix();
    glTranslatef(-3.505, 1.7, -9.2); // Ch?nh v? trí
    glScalef(0.1, 1.4, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();

    // ve thanh doc ngan 3 cua tu sach
    glColor3f(180.0 / 255.0, 95.0 / 255.0, 4.0 / 255.0); // Màu #B45F04
    glPushMatrix();
    glTranslatef(1.5, 1.7, -9.2); // Ch?nh v? trí
    glScalef(0.1, 1.4, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();

    // ve thanh doc ngan 4 cua tu sach
    glColor3f(180.0 / 255.0, 95.0 / 255.0, 4.0 / 255.0); // Màu #B45F04
    glPushMatrix();
    glTranslatef(5.0, -1.4, -9.2); // Ch?nh v? trí
    glScalef(0.1, 1.3, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();

    // ve thanh doc lan giua cua tu sach
    glColor3f(180.0 / 255.0, 95.0 / 255.0, 4.0 / 255.0); // Màu #B45F04
    glPushMatrix();
    glTranslatef(1.5, 0.3, -9.2); // Ch?nh v? trí
    glScalef(0.1, 4.4, 0.2); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();

    // ve thanh doc lan giua cua tu sach
    glColor3f(180.0 / 255.0, 95.0 / 255.0, 4.0 / 255.0); // Màu #B45F04
    glPushMatrix();
    glTranslatef(-3.5, 0.3, -9.2); // Ch?nh v? trí
    glScalef(0.1, 4.4, 0.2); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();


    //ve cac thanh ngang giua
    glColor3f(180.0 / 255.0, 95.0 / 255.0, 4.0 / 255.0); // Màu #B45F04
    glPushMatrix();
    glTranslatef(-1, 1, -9.2); // Ch?nh v? trí
    glScalef(8, 0.1, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();


    //ve thanh ngang 1
    glColor3f(180.0 / 255.0, 95.0 / 255.0, 4.0 / 255.0); // Màu #B45F04
    glPushMatrix();
    glTranslatef(-7.1, -0.8, -9.2); // Ch?nh v? trí
    glScalef(4.3, 0.05, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();

    //ve thanh ngang 2
    glColor3f(180.0 / 255.0, 95.0 / 255.0, 4.0 / 255.0); // Màu #B45F04
    glPushMatrix();
    glTranslatef(5, -0.8, -9.2); // Ch?nh v? trí
    glScalef(4, 0.05, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();

    //ve thanh ngang 3
    glColor3f(180.0 / 255.0, 95.0 / 255.0, 4.0 / 255.0); // Màu #B45F04
    glPushMatrix();
    glTranslatef(8.5, -1, -9.2); // Ch?nh v? trí
    glScalef(3, 0.05, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();

    // ve thanh ngang ngan 1 cua tu sach
    glColor3f(180.0 / 255.0, 95.0 / 255.0, 4.0 / 255.0); // Màu #B45F04
    glPushMatrix();
    glTranslatef(-5.8, 1, -9.2); // Ch?nh v? trí
    glScalef(1.4, 0.1, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();

    // ve thanh ngang ngan 2 cua tu sach
    glColor3f(180.0 / 255.0, 95.0 / 255.0, 4.0 / 255.0); // Màu #B45F04
    glPushMatrix();
    glTranslatef(-5.8, 0, -9.2); // Ch?nh v? trí
    glScalef(1.4, 0.1, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();

    // ve thanh ngang ngan 3 cua tu sach
    glColor3f(180.0 / 255.0, 95.0 / 255.0, 4.0 / 255.0); // Màu #B45F04
    glPushMatrix();
    glTranslatef(-4.2, -0.1, -9.2); // Ch?nh v? trí
    glScalef(1.4, 0.1, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();

    // ve thanh ngang ngan 4 cua tu sach
    glColor3f(180.0 / 255.0, 95.0 / 255.0, 4.0 / 255.0); // Màu #B45F04
    glPushMatrix();
    glTranslatef(-4.2, -1, -9.2); // Ch?nh v? trí
    glScalef(1.4, 0.1, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();

    // ve thanh ngang ngan 5 cua tu sach
    glColor3f(180.0 / 255.0, 95.0 / 255.0, 4.0 / 255.0); // Màu #B45F04
    glPushMatrix();
    glTranslatef(2.25, -0.1, -9.2); // Ch?nh v? trí
    glScalef(1.4, 0.1, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();

    // ve thanh ngang ngan 6 cua tu sach
    glColor3f(180.0 / 255.0, 95.0 / 255.0, 4.0 / 255.0); // Màu #B45F04
    glPushMatrix();
    glTranslatef(2.25, -1, -9.2); // Ch?nh v? trí
    glScalef(1.4, 0.1, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();

    // ve thanh ngang ngan 7 cua tu sach
    glColor3f(180.0 / 255.0, 95.0 / 255.0, 4.0 / 255.0); // Màu #B45F04
    glPushMatrix();
    glTranslatef(3.5, 1, -9.2); // Ch?nh v? trí
    glScalef(1, 0.1, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();

    // ve thanh ngang ngan 8 cua tu sach
    glColor3f(180.0 / 255.0, 95.0 / 255.0, 4.0 / 255.0); // Màu #B45F04
    glPushMatrix();
    glTranslatef(3.5, 0.0, -9.2); // Ch?nh v? trí
    glScalef(1, 0.1, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();

    // ve thanh ngang ngan 9 cua tu sach
    glColor3f(180.0 / 255.0, 95.0 / 255.0, 4.0 / 255.0); // Màu #B45F04
    glPushMatrix();
    glTranslatef(6.5, 0.0, -9.2); // Ch?nh v? trí
    glScalef(1, 0.1, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();

    //ve chan tu  
    glColor3f(180.0 / 255.0, 95.0 / 255.0, 4.0 / 255.0); // Màu #B45F04
    glPushMatrix();
    glTranslatef(-7, -3.5, -9.2); // Ch?nh v? trí
    glScalef(6, 3, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();

    //ve chan tu 2 
    glColor3f(180.0 / 255.0, 95.0 / 255.0, 4.0 / 255.0); // Màu #B45F04
    glPushMatrix();
    glTranslatef(6, -3.5, -9.2); // Ch?nh v? trí
    glScalef(8, 3, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();
    
    //ve cua tu 1
    glColor3f(141 / 255.0, 86.0 / 255.0, 27.0 / 255.0); // Màu nau nhat
    glPushMatrix();
    glTranslatef(-6.25, -3.5, -9); // Ch?nh v? trí
    glScalef(1.4,2.5, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();
    
    
    //ve cua tu 2
    glColor3f(141 / 255.0, 86.0 / 255.0, 27.0 / 255.0); // Màu nau nhat
    glPushMatrix();
    glTranslatef(-4.75, -3.5, -9); // Ch?nh v? trí
    glScalef(1.4,2.5, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();
    
      //ve cua tu 3
    glColor3f(141 / 255.0, 86.0 / 255.0, 27.0 / 255.0); // Màu nau nhat
    glPushMatrix();
    glTranslatef(2.75, -3.5, -9); // Ch?nh v? trí
    glScalef(1.4,2.5, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();
    
    //ve cua tu 4
    glColor3f(141 / 255.0, 86.0 / 255.0, 27.0 / 255.0); // Màu nau nhat
    glPushMatrix();
    glTranslatef(6.25, -3.5, -9); // Ch?nh v? trí
    glScalef(1.4,2.5, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();
    
    
    //ve ngan keo tu 1
    glColor3f(141 / 255.0, 86.0 / 255.0, 27.0 / 255.0); // Màu nau nhat
    glPushMatrix();
    glTranslatef(4.5, -2.65, -9); // Ch?nh v? trí
    glScalef(2,0.75, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();
    
    //ve ngan keo tu 2
    glColor3f(141 / 255.0, 86.0 / 255.0, 27.0 / 255.0); // Màu nau nhat
    glPushMatrix();
    glTranslatef(4.5, -3.5, -9); // Ch?nh v? trí
    glScalef(2,0.75, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();


	//ve ngan keo tu 3
    glColor3f(141 / 255.0, 86.0 / 255.0, 27.0 / 255.0); // Màu nau nhat
    glPushMatrix();
    glTranslatef(4.5, -4.35, -9); // Ch?nh v? trí
    glScalef(2,0.75, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();

	// ve vien tu 1
    glColor3f(1,1,1); // Màu trang
    glPushMatrix();
    glTranslatef(-5.5, -2.2, -9); // Ch?nh v? trí
    glScalef(3,0.05, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();
    
    // ve vien tu 2
    glColor3f(1,1,1); // Màu trang
    glPushMatrix();
    glTranslatef(-5.5, -3.5, -9); // Ch?nh v? trí
    glScalef(0.05,2.5, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();

	// ve vien tu 3
    glColor3f(1,1,1); // Màu trang
    glPushMatrix();
    glTranslatef(4, -2.2, -9); // Ch?nh v? trí
    glScalef(6,0.1, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();
    
    // ve vien tu 4
    glColor3f(1,1,1); // Màu trang
    glPushMatrix();
    glTranslatef(4.5, -3.1, -9); // Ch?nh v? trí
    glScalef(2,0.1, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();
    
    // ve vien tu 5
    glColor3f(1,1,1); // Màu trawng
    glPushMatrix();
    glTranslatef(4.5, -3.9, -9); // Ch?nh v? trí
    glScalef(2,0.1, 1.5); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích th??c 1x1x1
    glPopMatrix();
}

void LowChair(){
	// Khung gi??ng
    glColor3f(0.5f, 0.3f, 0.1f); // Màu nâu
    glPushMatrix();
    glTranslatef(-8.5,-4, -3.5); // D?ch chuy?n xu?ng
    glScalef(2.8, 1.8, 10); // Kích th??c khung gi??ng
    glutSolidCube(1.0f);
    glPopMatrix();

    // M?t gi??ng
    glColor3f(1,192/255,253/255); // Màu tr?ng
    glPushMatrix();
    glTranslatef(-8.5,-3, -3.5); // D?ch chuy?n xu?ng
   	glScalef(3, 0.2, 10); // Kích th??c khung gi??ng
    glutSolidCube(1.0f);
    glPopMatrix();
    
    // khung duoi dem 
    glColor3f(0.5f, 0.3f, 0.1f); // Màu nâu
    glPushMatrix();
    glTranslatef(-7.05,-3.2, -3.5); // D?ch chuy?n xu?ng
    glScalef(0.1,0.2, 10); // Kích th??c khung gi??ng
    glutSolidCube(1.0f);
    glPopMatrix();

    // khung duoi giuong 
    glColor3f(0.9f, 0.9f, 0.9f); // Màu tr?ng
    glPushMatrix();
    glTranslatef(-7.05,-4.2, -3.5); // D?ch chuy?n xu?ng
   	glScalef(0.1,1.8, 10); // Kích th??c khung gi??ng
    glutSolidCube(1.0f);
    glPopMatrix();
}

void Closets(){
	//ve tu trai ben cua so
    glColor3f(141 / 255.0, 86.0 / 255.0, 27.0 / 255.0); // Màu nau nhat
    glPushMatrix();
    glTranslatef(9.15, -1.4,7); // Ch?nh v? trí
    glScalef(1.5,7,4); // T? l? kích thu?c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích thu?c 1x1x1
    glPopMatrix();
    
    //ve noc tu
    glColor3f(1,1,1); // Màu trang
    glPushMatrix();
    glTranslatef(9.15,2.15,7); // Ch?nh v? trí
    glScalef(1.5,0.1,4); // T? l? kích thu?c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích thu?c 1x1x1
    glPopMatrix();
    
    //ve ngan giua tu
    glColor3f(1,1,1); // Màu trang
    glPushMatrix();
    glTranslatef(9.15, -1.4,7); // Ch?nh v? trí
    glScalef(1.6,7,0.1); // T? l? kích thu?c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích thu?c 1x1x1
    glPopMatrix();
    
    // ve cua tu nho trai
    glColor3f(1,1,1); // Màu trang
    glPushMatrix();
    glTranslatef(9.15,-3,6); // Ch?nh v? trí
    glScalef(1.55,0.1,1.9); // T? l? kích thu?c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích thu?c 1x1x1
    glPopMatrix();
    
     // ve cua tu nho phai
    glColor3f(1,1,1); // Màu trang
    glPushMatrix();
    glTranslatef(9.15,0,8); // Ch?nh v? trí
    glScalef(1.55,0.1,1.9); // T? l? kích thu?c x5
    glutSolidCube(1.0); // V? m?t h?nh h?p ch? nh?t kích thu?c 1x1x1
    glPopMatrix();
} 

// ------------------------------ //


void Room() {
    // V? tr?n nhà
    glColor3f(1.0, 0.5, 0.7);
    glPushMatrix();
    glTranslatef(0.0f, 5.0f, 0.0f);
    glScalef(20.0f, 0.2f, 20.0f);
    glutSolidCube(1.0f);
    glPopMatrix();

    // V? sàn c?n ph?ng
    glColor3f(1.0, 1.0, 1.0);
    glPushMatrix();
    glTranslatef(0.0f, -5.0f, 0.0f);
    glScalef(20.0f, 0.2f, 20.0f);
    glutSolidCube(1.0f);
    glPopMatrix();

    // V? t??ng c?n ph?ng
    // T??ng m?t sau
    //glColor3f(127.0/255, 167.0/255, 245.0/255);
    glPushMatrix();
    glTranslatef(0.0f, 0.0f, -10.0f);
    glScalef(20.0f, 10.0f, 0.2f);
    glutSolidCube(1.0f);
    glPopMatrix();

    // T??ng m?t trái
    glColor3f(0.0, 1.0, 1.0);
    glPushMatrix();
    glTranslatef(-10.0f, 0.0f, 0.0f);
    glScalef(0.2f, 10.0f, 20.0f);
    glutSolidCube(1.0f);
    glPopMatrix();
    
    // T??ng m?t ph?i
    glColor3f(0.0, 1.0, 1.0);
    glPushMatrix();
    glTranslatef(10.0f, 0.0f, 0.0f);
    glScalef(0.2f, 10.0f, 20.0f);
    glutSolidCube(1.0f);
    glPopMatrix();
}

void Table() {
    // bàn và gh? 1
    // Chân 1
    glColor3f(0.4, 0.6, 0.8); // Màu xanh n??c bi?n
    glPushMatrix();
    glTranslatef(-3.9, -3.5, -8.5);
    glScalef(1.0, 14, 15.0);
    glutSolidCube(0.2);
    glPopMatrix();

    // Chân 2
    glPushMatrix();
    glTranslatef(1.9, -3.5, -8.5);
    glScalef(1.0, 14, 15.0);
    glutSolidCube(0.2);
    glPopMatrix();

    // M?t bàn

    glPushMatrix();
    glTranslatef(-1.0, -2.1, -8.5);
    glScalef(30.0, 1, 15.0);
    glutSolidCube(0.2);
    glPopMatrix();

    // v? gh?
    // chân 1
    glColor3f(0.7, 0.3, 0.2); // Màu ?? nâu
    glPushMatrix();
    glTranslatef(0.0, -4, -5);
    glScalef(1.0, 10, 1.0);
    glutSolidCube(0.2);
    glPopMatrix();

    // chân 2
    glPushMatrix();
    glTranslatef(-1.5, -4, -5);
    glScalef(1.0, 10, 1.0);
    glutSolidCube(0.2);
    glPopMatrix();
    // chân 3
    glPushMatrix();
    glTranslatef(0.0, -4, -6.5);
    glScalef(1.0, 10, 1.0);
    glutSolidCube(0.2);
    glPopMatrix();

    // chân 4
    glPushMatrix();
    glTranslatef(-1.5, -4, -6.5);
    glScalef(1.0, 10, 1.0);
    glutSolidCube(0.2);
    glPopMatrix();

    // m?t gh?
    glPushMatrix();
    glTranslatef(-0.8, -3, -5.7);
    glScalef(10.0, 1, 10.0);
    glutSolidCube(0.2);
    glPopMatrix();

    // l?ng gh?
    glPushMatrix();
    glTranslatef(-0.8, -2, -4.9);
    glScalef(10.0, 9, 1.0);
    glutSolidCube(0.2);
    glPopMatrix();

    // bàn và gh? 2
     // Chân 1
    glColor3f(0.4, 0.6, 0.8); // Màu xanh n??c bi?n
    glPushMatrix();
    glTranslatef(8.55, -3.5, -8.35);
    glScalef(15.0, 14, 1.0);
    glutSolidCube(0.2);
    glPopMatrix();

    // Chân 2
    glPushMatrix();
    glTranslatef(8.55, -3.5, -2.55);
    glScalef(15.0, 14, 1.0);
    glutSolidCube(0.2);
    glPopMatrix();

    // M?t bàn
    glPushMatrix();
    glTranslatef(8.5, -2.0, -5.45);
    glScalef(15.0, 1, 30.0);
    glutSolidCube(0.2);
    glPopMatrix();

    // chân 1
    glColor3f(0.7, 0.3, 0.2); // Màu ?? nâu
    glPushMatrix();
    glTranslatef(6.0, -4, -4);
    glScalef(1.0, 10, 1.0);
    glutSolidCube(0.2);
    glPopMatrix();
    // chân 2
    glPushMatrix();
    glTranslatef(6.0, -4, -5.5);
    glScalef(1.0, 10, 1.0);
    glutSolidCube(0.2);
    glPopMatrix();
    // chân 3
    glPushMatrix();
    glTranslatef(4.5, -4, -4);
    glScalef(1.0, 10, 1.0);
    glutSolidCube(0.2);
    glPopMatrix();
    // chân 4
    glPushMatrix();
    glTranslatef(4.5, -4, -5.5);
    glScalef(1.0, 10, 1.0);
    glutSolidCube(0.2);
    glPopMatrix();

    // m?t gh?
    glPushMatrix();
    glTranslatef(5.3, -2.9, -4.75);
    glScalef(10.0, 1, 10.0);
    glutSolidCube(0.2);
    glPopMatrix();
    // l?ng gh?
    glPushMatrix();
    glTranslatef(4.40, -1.9, -4.75);
    glScalef(1.0, 9, 10.0);
    glutSolidCube(0.2);
    glPopMatrix();

}

void RightWindow(){
	/////////// c?a s? bên ph?i
    glColor3f(0.0, 0.0, 0.0); // Màu xanh nh?t v?i ?? trong su?t
    glPushMatrix(); // L?u ma tr?n hi?n t?i
    glTranslatef(9.85, -1.0, 0.0); // Di chuy?n ??n v? trí c?a s? trên màn h?nh
    glRotatef(90.0, 0.0, 1.0, 0.0); // Xoay h?nh 90 ?? quanh tr?c y
    glLineWidth(8.0);
    glBegin(GL_LINE_LOOP);
    glVertex2f(-1.0, 0.0);
    glVertex2f(-1.0, 5.0);
    glVertex2f(8.0, 5.0);
    glVertex2f(8.0, 0.0);
    glEnd();
    glPopMatrix(); // Khôi ph?c ma tr?n ban ??u

    glPushMatrix(); // L?u ma tr?n hi?n t?i
    glTranslatef(9.85, -1.0, 0.0); // Di chuy?n ??n v? trí c?a s? trên màn h?nh
    glRotatef(90.0, 0.0, 1.0, 0.0); // Xoay h?nh 90 ?? quanh tr?c y
    glLineWidth(8.0);
    glBegin(GL_LINES); // S? d?ng GL_LINES thay v? GL_LINE_LOOP
    glVertex2f(-1.0, 3.5);
    glVertex2f(8.0, 3.5);
    glVertex2f(3.35, 0.0);
    glVertex2f(3.35, 5.0);
    glEnd();
    glPopMatrix(); // Khôi ph?c ma tr?n ban ??u

    ///// kính
    glColor4f(1.0, 1.0, 1.0, 0.5); // Màu xanh nh?t v?i ?? trong su?t
    glPushMatrix();
    glTranslatef(9.9f, 0.75f, -1.2f);
    glScalef(0.19, 3.35, 4.2); // T? l? kích th??c x5
    glutSolidCube(1.0f);
    glPopMatrix();

    glColor4f(1.0, 1.0, 1.0, 0.5); // Màu xanh nh?t v?i ?? trong su?t
    glPushMatrix();
    glTranslatef(9.9f, 0.75f, -5.7f);
    glScalef(0.19, 3.35, 4.5); // T? l? kích th??c x5
    glutSolidCube(1.0f);
    glPopMatrix();

    glColor4f(1.0, 1.0, 1.0, 0.5); // Màu xanh nh?t v?i ?? trong su?t
    glPushMatrix();
    glTranslatef(9.9f, 3.25f, -5.7f);
    glScalef(0.19, 1.35, 4.5); // T? l? kích th??c x5
    glutSolidCube(1.0f);
    glPopMatrix();

    glColor4f(1.0, 1.0, 1.0, 0.5); // Màu xanh nh?t v?i ?? trong su?t
    glPushMatrix();
    glTranslatef(9.9f, 3.25f, -1.2f);
    glScalef(0.19, 1.35, 4.2); // T? l? kích th??c x5
    glutSolidCube(1.0f);
    glPopMatrix();
}

void LeftWindow(){
	////////////// c?a s? bên trái
    glColor3f(0.0, 0.0, 0.0); // Màu xanh nh?t v?i ?? trong su?t
    glPushMatrix(); // L?u ma tr?n hi?n t?i
    glTranslatef(-9.85, -1.6, -3.0); // Di chuy?n ??n v? trí c?a s? trên màn h?nh
    glRotatef(90.0, 0.0, 1.0, 0.0); // Xoay h?nh 90 ?? quanh tr?c y
    glLineWidth(8.0);
    glBegin(GL_LINE_LOOP);
    glVertex2f(-1.0, 0.0);
    glVertex2f(-1.0, 4.0);
    glVertex2f(4.0, 4.0);
    glVertex2f(4.0, 0.0);
    glEnd();
    glPopMatrix(); // Khôi ph?c ma tr?n ban ??u


    glPushMatrix(); // L?u ma tr?n hi?n t?i
    glTranslatef(-9.85, -1.6, -3.0); // Di chuy?n ??n v? trí c?a s? trên màn h?nh
    glRotatef(90.0, 0.0, 1.0, 0.0); // Xoay h?nh 90 ?? quanh tr?c y
    glLineWidth(8.0);
    glBegin(GL_LINES); // S? d?ng GL_LINES thay v? GL_LINE_LOOP
    glVertex2f(1.55, 0.0);
    glVertex2f(1.55, 4.0);
    glEnd();
    glPopMatrix(); // Khôi ph?c ma tr?n ban ??u

    // Kính c?a s?
    glColor4f(1.0, 1.0, 1.0, 0.5); // Màu xanh nh?t v?i ?? trong su?t
    glPushMatrix();
    glTranslatef(-9.85, 0.4, -3.25); // Ch?nh v? trí
    glScalef(0.19, 3.85, 2.35); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? kính c?a s?
    glPopMatrix();

    glColor4f(1.0, 1.0, 1.0, 0.5); // Màu xanh nh?t v?i ?? trong su?t
    glPushMatrix();
    glTranslatef(-9.85, 0.4, -5.75); // Ch?nh v? trí
    glScalef(0.19, 3.85, 2.35); // T? l? kích th??c x5
    glutSolidCube(1.0); // V? kính c?a s?
    glPopMatrix();
}

void Door() {
    //////////// v? c?a 
    glColor3f(0.3f, 0.1f, 0.0f);
    glPushMatrix(); // L?u ma tr?n hi?n t?i
    glTranslatef(-9.87, -4.9, 7.0); // Di chuy?n ??n v? trí c?a s? trên màn h?nh
    glRotatef(90.0, 0.0, 1.0, 0.0); // Xoay h?nh 90 ?? quanh tr?c y
    glLineWidth(8.0);
    glBegin(GL_POLYGON);
    glVertex2f(-1.0, 0.0);
    glVertex2f(-1.0, 7.5);
    glVertex2f(4.0, 7.5);
    glVertex2f(4.0, 0.0);
    glEnd();
    glPopMatrix(); // Khôi ph?c ma tr?n ban ??u

    glPushMatrix(); // L?u ma tr?n hi?n t?i
    glColor3f(1.0, 1.0, 1.0); // ??t màu v? là tr?ng
    glTranslatef(-9.85, -1, 4.0); // Di chuy?n ??n v? trí c?a s? trên màn h?nh
    glRotatef(90.0, 0.0, 1.0, 0.0); // Xoay h?nh 90 ?? quanh tr?c y
    glutSolidSphere(0.25, 50, 50); // V? m?t h?nh c?u v?i bán kính 0.5
    glPopMatrix();
}

// ------------------------------ //

void light1(){
	////
	glPushMatrix();
    GLfloat light_amb1[]  = {0.5, 0.5, 0.5, 1.0};
    GLfloat light_diff1[]  = { 1.0, 1.0, 1.0, 1.0 };
    GLfloat light_spec1[] = { 1.0, 1.0, 1.0, 1.0 };
    GLfloat light_pos1[] = { 8.5, 0.0, 1.0, 1.0 }; 
	
    glLightfv(GL_LIGHT1, GL_POSITION, light_pos1);
    glLightfv(GL_LIGHT1, GL_AMBIENT, light_amb1);
    glLightfv(GL_LIGHT1, GL_DIFFUSE, light_diff1);
    glLightfv(GL_LIGHT1, GL_SPECULAR, light_spec1);
    
    GLfloat spot_direction1[] = { -0.3, -0.5, -1.5 };
    glLightfv(GL_LIGHT1, GL_SPOT_DIRECTION, spot_direction1);
    
	glLightf(GL_LIGHT1, GL_SPOT_EXPONENT, 50.0);
    glLightf( GL_LIGHT1, GL_SPOT_CUTOFF, 50.0);
    
    glPopMatrix();
    glutPostRedisplay();
}

void light2(){
	glPushMatrix();
    GLfloat light_amb2[]  = {0.5, 0.5, 0.5, 1.0};
    GLfloat light_diff2[]  = { 1.0, 1.0, 1.0, 1.0 };
    GLfloat light_spec2[] = { 1.0, 1.0, 1.0, 1.0 };
    GLfloat light_pos2[] = { -8.5, 1.0, -1.0, 1.0 }; 
	
    glLightfv(GL_LIGHT2, GL_POSITION, light_pos2);
    glLightfv(GL_LIGHT2, GL_AMBIENT, light_amb2);
    glLightfv(GL_LIGHT2, GL_DIFFUSE, light_diff2);
    glLightfv(GL_LIGHT2, GL_SPECULAR, light_spec2);
    
    GLfloat spot_direction2[] = { 1.0, -0.5, -1.0 };
    glLightfv(GL_LIGHT2, GL_SPOT_DIRECTION, spot_direction2);
    
	glLightf(GL_LIGHT2, GL_SPOT_EXPONENT, 50.0);
    glLightf( GL_LIGHT2, GL_SPOT_CUTOFF, 20.0);
    
    glPopMatrix();
    glutPostRedisplay();
}

void light3(){
	glPushMatrix();
    GLfloat light_amb3[]  = {0.3, 0.3, 0.3, 1.0};
    GLfloat light_diff3[]  = { 1.0, 1.0, 1.0, 1.0 };
    GLfloat light_spec3[] = { 1.0, 1.0, 1.0, 1.0 };
    GLfloat light_pos3[] = { 0.0, 9.7, 0.0, 1.0 }; 
	
    glLightfv(GL_LIGHT3, GL_POSITION, light_pos3);
    glLightfv(GL_LIGHT3, GL_AMBIENT, light_amb3);
    glLightfv(GL_LIGHT3, GL_DIFFUSE, light_diff3);
    glLightfv(GL_LIGHT3, GL_SPECULAR, light_spec3);
    
    GLfloat spot_direction3[] = { 0.0, -1.0, 0.0 };
    glLightfv(GL_LIGHT3, GL_SPOT_DIRECTION, spot_direction3);
    
	glLightf(GL_LIGHT3, GL_SPOT_EXPONENT, 5.0);
    glLightf( GL_LIGHT3, GL_SPOT_CUTOFF, 90.0);
    
    glPopMatrix();
    glutPostRedisplay();
}

void display()
{
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glEnable(GL_DEPTH_TEST);// b?t ch? ?? kh? m?t khu?t
    //glEnable(GL_LIGHTING);
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
	
    gluLookAt(eyeX, eyeY, eyeZ,
        	  refX, refY, refZ,
        	  0.0f, 1.0f, 0.0f);
        	  
    glEnable(GL_LIGHTING);
    // ------------- //
    TrucToaDo();
    
    Bookshelf();
    LowChair();
    Closets();
    
    Room();
    Table();
    RightWindow();
    LeftWindow();
    Door();
    
    drawBook();
    PlantPot();
    VietNam();
    PictureFrame();
    Bottle();
    TeaPot();
    Pyramid();
    Ball();
    Bin();
    Trophy();
    Lamp();
    BookPen();
    Other();
    CeilingLights();
    Carpet();
    Carpet1();
    
    light1();
    light2();
    light3();
    
    glDisable(GL_LIGHTING);
    // ------------- //
    
    glPopMatrix();
    glFlush();
    glutSwapBuffers();

}

void keyboard(unsigned char key, int x, int y)
{
    switch (key)
    {
    case 'q': eyeY += 0.5; break;
    case 'e': eyeY -= 0.5; break;
    case 'a': eyeX += 0.5; break;
    case 'd': eyeX -= 0.5; break;
    case 's': eyeZ += 0.25; break;
    case 'w': eyeZ -= 0.25; break;
    
    case 'r':
        eyeX = 0.0; eyeY = 0.0; eyeZ = 30.0;
        refX = 0.0; refY = 0.0; refZ = 0.0;
        break;
    case 'x':
        eyeX = -5.0; eyeY = 0.0; eyeZ = 0.0;
        refX = 0.0; refY = 0.0; refZ = 0.0;
        break;
    case 'z':
        eyeX = 5.0; eyeY = 0.0; eyeZ = 0.0;
        refX = 0.0; refY = 0.0; refZ = 0.0;
        break;
    case 'c':
        eyeX = 0.0; eyeY = 0.0; eyeZ = 5.0;
        refX = 0.0; refY = 0.0; refZ = 0.0;
        break;
    
    case 'i': refY += 0.2; break;
    case 'k': refY -= 0.2; break;
    case 'l': refX += 0.5; break;
    case 'j': refX -= 0.5; break;
    case 'u': refZ += 0.5; break;
    case 'o': refZ -= 0.5; break;

    case '4':
    	if (light0_on == false) 
			{ glEnable(GL_LIGHT0);light0_on = true;break; }
		else
			{ glDisable(GL_LIGHT0); light0_on = false; break; }
    case '1':
        if (light1_on == false) 
			{ glEnable(GL_LIGHT1);light1_on = true;break; }
		else
			{ glDisable(GL_LIGHT1); light1_on = false; break; }
    case '2':
        if (light2_on == false) 
			{ glEnable(GL_LIGHT2);light2_on = true;break; }
		else
			{ glDisable(GL_LIGHT2); light2_on = false; break; }
    case '3':
        if (light3_on == false) 
			{ glEnable(GL_LIGHT3);light3_on = true;break; }
		else
			{ glDisable(GL_LIGHT3); light3_on = false; break; }
    case 27:    // Escape key
        exit(1);
    }
    glutPostRedisplay();
}

int main(int argc, char** argv) {
	
	printf("Press ESC to close program\n");
	
	printf("\n");
	
	printf("r: Reset Eye point\n");
	printf("z: Left wall\n");
	printf("x: Right wall\n");
	printf("c: Middle wall\n");
	
	printf("\n");
	
	printf("To move the Eye point: \n");
	printf("s: +z axis\n");
	printf("w: -z axis\n");
	printf("a: +x axis\n");
	printf("d: -x axis\n");
	printf("q: +y axis\n");
	printf("e: -y axis\n");
	
	printf("\n");
	
	printf("To move the Camera point: \n");
	printf("u: +z axis\n");
	printf("o: -z axis\n");
	printf("l: +x axis\n");
	printf("j: -x axis\n");
	printf("i: +y axis\n");
	printf("k: -y axis\n");
	
	printf("\n");
	
	printf("To turn on/off lights: \n");
	printf("1: Light 1 \n");
	printf("2: Light 2 \n");
	printf("3: Light 3 \n");
	printf("4: Light ambient \n");
	
	printf("\n");
	
	
	
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
    glutInitWindowSize(800, 600);
    glutInitWindowPosition(100, 100);
    glutCreateWindow("Study Room");
    
	Lighting();
	
    glutDisplayFunc(display);
    glutReshapeFunc(ReShape);
    glutKeyboardFunc(keyboard);
    
    glutMainLoop();
    return 0;
}
