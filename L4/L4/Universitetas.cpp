#include "Universitetas.h"

Universitetas::Universitetas(){}
Universitetas::~Universitetas(){}

void Universitetas::nuskaitymas(string failoPavadinimas){
	ifstream duom(failoPavadinimas.c_str);
	duom >> destytojaiSk >> dienosSk[1] >> vidurkis;
	string vardas, pavarde, fakultetas, katedra;
	for (int i = 0; i < destytojaiSk; i++){
		duom >> vardas >> pavarde >> fakultetas >> katedra;
		destytojai[i] = Destytojas(vardas, pavarde, fakultetas, katedra);
	}
	int DienosDestytojui[dienosSk[i]];
	for (int i = 0; i < destytojaiSk; i++){
		for (int j = 0; i < dienosSk[1]; j++){
			duom >> DienosDestytojui[j];
		}
		destytojai[i].DetiDienas(DienosDestytojui[], 1, dienosSk[1]);
	}
	duom >> dienosSk[2];
	for (int i = 0; i < destytojaiSk; i++){
		for (int j = 0; i < dienosSk[2]; j++){
			duom >> DienosDestytojui[j];
		}
		destytojai[i].DetiDienas(DienosDestytojui[], 2, dienosSk[2]);
	}
}
void Universitetas::laisvuDienuNustatymas(int menesis){
	laisvuDienuSk[menesis] = 0;
	for (int i = 0; i < dienosSk[menesis]; i++){
		bool Patikrinimas = true;
		for (int j = 0; j < destytojuSk; j++){
			if (!destytojai[j].arDirboVisada(menesis, i)
				Patikrinimas = false;
		}
		if (Patikrinimas == false){
			laisvosDienos[laisvuDienuSk[menesis]] = i;
			laisvuDienuSk[menesis]++;
		}
	}
}
void Universitetas::rikiavimas(){
	Destytojas P;
	for (int i = 0; i < destytojaiSk - 1; i++)
		for (int j = i + 1; j < destytojaiSk; j++)
			if (destytojai[i].imtiFakulteta() > destytojai[j].imtiFakulteta())
			{
				P = destytojai[i];
				destytojai[i] = destytojai[j];
				destytojai[j] = P;
			}
}
void Universitetas::dirbtosValandos(){
	for (int i = 0; i < destytojaiSk; i++)
		destytojai[i].kruvioApskaiciavimas();
}
void Universitetas::maziauNustatymas(int menesis)
{
	mazaiDirbantysSk[menesis] = 0;
    for (int i = 0; i < destytojaiSk; i++)
		if (destytojai[i].Vidutinis()){
			mazaiDirbantys[mazaiDirbantysSk[menesis]][menesis] = destytojai[i];
			mazaiDirbantysSk[menesis]++;
		}
}
void isvedimas(string failoPavadinimas){
}