#include <iostream>
#include <fstream>

#define m 6											//버퍼에 들어가는 레코드 수
using namespace std;

int buffer[m]={0};									//버퍼-레코드 구현
int frozen[m]={0};									//동결된 레코드 구현
int run[50]={0};									//런 저장 공간 구현
int idx_run=0;										//런 기록 포인터	
int cnt_run=1;										//런 생성 수

int min(int b[]);
int index(int buffer[],int num);
void sort(int b[]);
void writeRun(ofstream& outStream);