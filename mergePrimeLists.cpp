#include<iostream>
#include<cmath>
#include<fstream>
#include<string>

using namespace std;

ifstream in1 , in2, in3, in4, in5, in6, in7,in8, in9, in10;
ofstream out;

void copy(ifstream &in, ofstream &out);

int main(int argc, char *argv[]){

    out.open(argv[1]);
    in1.open(argv[2]);
    in2.open(argv[3]);
    in3.open(argv[4]);
    in4.open(argv[5]);
    in5.open(argv[6]);
    in6.open(argv[7]);
    in7.open(argv[8]);
    in8.open(argv[9]);
    in9.open(argv[10]);
    in10.open(argv[11]);

    

    copy(in1, out);
    copy(in2, out);
    copy(in3, out);
    copy(in4, out);
    copy(in5, out);
    copy(in6, out);
    copy(in7, out);
    copy(in8, out);
    copy(in9, out);
    copy(in10, out);

    in1.close();
    in2.close();
    in3.close();
    in4.close();
    in5.close();
    in6.close();
    in7.close();
    in8.close();
    in9.close();
    in10.close();
    out.close();

}


void copy(ifstream &in, ofstream &out){
    while(!in.eof()){
        string str;
        in >> str;
        out << str << endl;
    }
}

