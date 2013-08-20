#pragma once
#include "Destytojas.h"
using namespace std;
class Universitetas{
	Destytojas destytojai[100];
	Destytojas mazaiDirbantys[100][2];
	int mazaiDirbantysSk[2]
	int destytojaiSk;
	int dienosSk[2];
	int vidurkis;
	int laisvosDienos[100][2];
	int laisvuDienuSk[2];
public:
	Universitetas();
	~Universitetas();
	void nuskaitymas(string failoPavadinimas);
	void isvedimas(string failoPavadinimas);
	void laisvuDienuNustatymas(int menesis);
	void rikiavimas();
	void dirbtosValandos();
	void maziauNustatymas(int menesis);  //nustatom, kurie dirbo mazai
};
