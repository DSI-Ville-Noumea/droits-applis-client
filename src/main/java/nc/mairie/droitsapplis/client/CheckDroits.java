package nc.mairie.droitsapplis.client;

import java.util.ArrayList;

import nc.mairie.droitsapplis.metier.DroitsApp;
import nc.mairie.technique.Transaction;

public class CheckDroits {

	final static String LISTE_DROITS="LISTE_DROITS";
/**
 * 
 * @param aTransaction
 * @param compte
 * @param appli
 * @return
 * @throws Exception
 */
	public static ArrayList<String> getListDroitsFromCompteAppli(Transaction aTransaction, String compte, String appli) throws Exception{
		ArrayList<DroitsApp> listDroits;
		ArrayList<String> listDroitsString;
		listDroits=DroitsApp.listerDroitsAppfromCompteAndApplication(aTransaction, compte, appli);
		listDroitsString=new ArrayList<String>(listDroits.size());
		for(int i=0; i<listDroits.size(); i++){
			listDroitsString.add(i,listDroits.get(i).getDroit().trim());
		}
		return listDroitsString;
	}
}
