#include <iostream>
#include <fstream>

#define m 6											//���ۿ� ���� ���ڵ� ��
using namespace std;

int buffer[m]={0};									//����-���ڵ� ����
int frozen[m]={0};									//����� ���ڵ� ����
int run[50]={0};									//�� ���� ���� ����
int idx_run=0;										//�� ��� ������	
int cnt_run=1;										//�� ���� ��

int min(int b[]);
int index(int buffer[],int num);
void sort(int b[]);
void writeRun(ofstream& outStream);