#include "Destytojas.h"

Destytojas::Destytojas(string pavardeR, string vardasR, string fakultetasR, string katedraR) 
	  : pavarde(pavardeR), vardas(vardasR), fakultetas(fakultetasR), katedra(katedraR) {};

Destytojas::~Destytojas(void){}

void Destytojas::kruvioApskaiciavimas(int n, int menesis){
	bendras[menesis] = 0;
	for (int i = 0; i < n; i++){
		bendras[menesis] += kruvis[i][menesis];
	}
}
int Destytojas::imtibendraKruvi(int menesis){
	return bendras[menesis];
}
string Destytojas::imtiFakulteta(){
	return fakultetas;
}
bool Destytojas::arDirboVisada(int menesis, int diena){
	if(kruvis[diena][menesis] != 0)
		return true;
	else
		return false;
}
bool Destytojas::Vidutinis(int BendrasVidurkis, int menesis){
	if (bendras[menesis] < BendrasVidurkis)
		return true;
	else
		return false;
}
void Destytojas::DetiDienas(int Dienos[], int menesis, int dienuSk){
	for (int i = 0; i < dienuSk; i++)
		kruvis[i][menesis] = Dienos[i][menesis];
}