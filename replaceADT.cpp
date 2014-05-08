#include "replace.h"

void replacementSelection()
{
	int input;
	ifstream inStream;
	inStream.open("input.txt");						//입력 화일

	ofstream outStream;
	outStream.open("run.txt");						//정렬된 서브 화일(런 생성 화일)

	for(int i=0; i<m; i++)
	{
		inStream>>buffer[i];						//버퍼에 입력 화일로 부터 m개의 레코드 판독
	}
	
	while(!inStream.eof())
	{
		int idx_frozen=0;
		while(idx_frozen<m)							//동결된 레코드 수가 버퍼의 레코드 수와 같아질 때까지 하나의 런 생성
		{
			if(inStream.eof())
				break;
			inStream>>input;						//입력 화일에서 다음 레코드를 판독
			cout<<"input: "<<input<<endl;			
			int key = min(buffer);					//버퍼에서 키 값이 가장 작은 레코드를 선택하여 출력
			int p = index(buffer,key);				//버퍼에서 위의 레코드가 위치한 인덱스
			
			if(input>key)							//입력 화일에서 읽어온 레코드와 버퍼에서 출력한 레코드와 대체
			{
				run[idx_run++] = buffer[p];			//버퍼에서 출력한 레코드는 런에 레코드로 출력
				//idx_run++;
				buffer[p] = input;
			}
			else									//입력 화일에서 읽어온 레코드가 버퍼에서 출력한 레코드보다 작은경우
			{
				frozen[idx_frozen++] = input;		//입력 화일에서 읽어온 레코드에 동결"frozen"표시
				//idx_frozen++;
				run[idx_run++] = buffer[p];			//이 때 버퍼에서 출력한 레코드는 런의 레코드로 출력
				buffer[p] = -1;						//버퍼에서 위 레코드는 키 값이 가장 작은 레코드를 선택할 때 제외 

			}
		} 
		if(idx_frozen!=0)
			cout<<"frozen of run "<<cnt_run<<endl;
		for(int i=0; i<idx_frozen; i++)			//각 런마다 동결"frozen"레코드 확인
			cout<<frozen[i]<<endl;

		if(idx_frozen!=m)							//입력화일의 레코드를 모두 판독하여 대체선택이 끝난경우
		{											//버퍼에 남아있는 나머지 레코드들을
			//int n = min(buffer);					//내부정렬하여 현재 런에 레코드를 추가
			//while(n!=-1){
			//	int q = index(buffer,n);
			//	run[idx_run++] = buffer[q];
			//	buffer[q]=-1;
			//	n = min(buffer);}
			sort(buffer);
		}
		writeRun(outStream);						//런을 화일로 출력
			
		for(int i=0; i<idx_run; i++)				//새로우 런 생성을 위해 런 저장공간 초기화
			run[i] = 0;
		idx_run=0;									//런 기록 포인터 초기화

		if(idx_frozen==m){							//새로운 런 생성을 위해
			for(int i=0; i<m; i++){
				buffer[i] = frozen[i];				//동결된 레코드를 모두 해제
				frozen[i] = 0;}}					//동결된 레코드 구현 공간 초기화
		
		else if(idx_frozen==0)						//입력화일의 레코드를 모두 판독하여 대체선택
			break;									//끝난 경우 런을 생성할 동결된 레코드가 없는 경우

		else{										//입력화일의 레코드를 모두 판독하여 대체선택이 끝난 경우
			sort(frozen);							//남아있는 동결된 레코드들을 내부 정렬하여 현재 런에 레코드를 추가
			writeRun(outStream);					//런을 화일로 출력
		}


	}
}

void writeRun(ofstream& outStream)					//런 저장공간에 있는 레코드들을 화일로 출력해주는 함수
{
	outStream<<"run "<<cnt_run++<<": ";
	for(int i=0; i<idx_run; i++)
		outStream<<run[i]<<" ";
	outStream<<endl;
}
void sort(int b[])								//대체 선택이 끝난 경우 버퍼 또는 동결된 레코드를 내부정렬하는 함수
{
	int temp;
	for(int i=0; i<m; i++)
	{
		for(int j=0; j<m-(i+1);  j++)
		{
			if(b[j]>b[j+1])
			{
				temp=b[j+1];
				b[j+1]=b[j];
				b[j]=temp;
			}
		}
	}
	for(int i=0; i<m ;i++)
		if(b[i]!=0 && b[i]!=-1)
			run[idx_run++] = b[i];
}

int index(int b[],int key)						//버퍼의 최소값을 가지는 레코드의 위치를 찾는 함수
{
	int idx;
	for(int i=0; i<m; i++)
	{
		if(b[i] == key)
			idx = i;
	}
	return idx;
}
int min(int b[])									//버퍼의 최소값을 가지는 레코드를 찾는 함수
{
	int temp;
	for(int i=0; i<m; i++)
	{
		for(int j=0; j<m-(i+1);  j++)				//버블 정렬을 사용하여 내림차순 정렬 후
		{
			if(b[j]>b[j+1])
			{
				temp=b[j+1];
				b[j+1]=b[j];
				b[j]=temp;
			}
		}
	}
	int i=0;
	for(i=0; i<m; i++)								//최소값을 가지는 레코드를 선택할 때 제외된 레코드를 판독하여
	{
		if(b[i]!=-1){
			temp = b[i];							//최소값을 가지는 레코드를 판독
			break;}
	}
	if(i==m)										//버퍼에 판독할 레코드가 없는 경우 
		return -1;
	return temp;									//최소값을 가지는 레코드를 반환
}

void main()
{
	replacementSelection();
	cout<<"GIT";
}