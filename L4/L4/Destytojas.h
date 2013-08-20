#pragma once
#include <string>
using namespace std;
class Destytojas
{
private:
	string pavarde, vardas, fakultetas, katedra;
	int bendras[2]; //valandu skaicius
	int kruvis[30][2]; //destytojo kruvis
public:
	Destytojas(string pavardeR, string vardasR, string fakultetasR, string katedraR) :
	  : pavarde(pavardeR), vardas(vardasR), fakultetas(fakultetasR), katedra(katedraR) {};
	~Destytojas();
	void kruvioApskaiciavimas(int n, int menesis);//kiek yra dienu
	int imtibendraKruvi(int menesis);
	void DetiDienas(int Dienos[], int menesis, int dienuSk);
	string imtiFakulteta();
	bool arDirboVisada(int menesis, int diena);
	bool Vidutinis(int BendrasVidurkis, int menesis);
};

