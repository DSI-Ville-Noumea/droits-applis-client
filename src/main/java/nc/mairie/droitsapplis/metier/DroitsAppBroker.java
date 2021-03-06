package nc.mairie.droitsapplis.metier;

import nc.mairie.technique.BasicRecord;
/**
 * Broker de l'Objet métier DroitsApp
 */
public class DroitsAppBroker extends nc.mairie.technique.BasicBroker {
/**
 * Constructeur DroitsAppBroker.
 * @param aMetier aMetier 
 */
public DroitsAppBroker(nc.mairie.technique.BasicMetier aMetier) {
	super(aMetier);
}
/**
 * @return src/nc.mairie.droitsapplis.metier.DroitsAppMetier
 */
protected nc.mairie.technique.BasicMetier definirMyMetier() {
	return new DroitsApp() ;
}
/**
 * @return src/nc.mairie.droitsapplis.metier.DroitsAppMetier
 */
protected DroitsApp getMyDroitsApp() {
	return (DroitsApp)getMyBasicMetier();
}
/**
 * Retourne le nom de la table.
 */
protected java.lang.String definirNomTable() {
	return "MAIRIE.SIDRAP";
}
/**
 * Retourne le mappage de chaque colonne de la table.
 */
protected java.util.Hashtable<String, BasicRecord> definirMappageTable() throws NoSuchFieldException {
	java.util.Hashtable<String, BasicRecord>  mappage = new java.util.Hashtable<String, BasicRecord> ();
	mappage.put("CDDRAP", new BasicRecord("CDDRAP", "INTEGER", getMyDroitsApp().getClass().getField("cddrap"), "IDENTITY"));
	mappage.put("APPLICATION", new BasicRecord("APPLICATION", "CHAR", getMyDroitsApp().getClass().getField("application"), "STRING"));
	mappage.put("DROIT", new BasicRecord("DROIT", "CHAR", getMyDroitsApp().getClass().getField("droit"), "STRING"));
	return mappage;
}
/**
 * Methode creerObjetMetierBroker qui retourne
 * true ou false
 * @param aTransaction aTransaction
 * @return boolean
 * @throws Exception Exception 
 */
public boolean creerDroitsApp(nc.mairie.technique.Transaction aTransaction)  throws Exception{
	return creer(aTransaction);
}
/**
 * Methode modifierObjetMetierBroker qui retourne
 * true ou false
 * @param aTransaction aTransaction
 * @return boolean
 * @throws Exception Exception 

 */
public boolean modifierDroitsApp(nc.mairie.technique.Transaction aTransaction) throws java.lang.Exception {
	return modifier(aTransaction);
}
/**
 * Methode supprimerObjetMetierBroker qui retourne
 * true ou false
 * @param aTransaction aTransaction
 * @return boolean
 * @throws Exception Exception 
 */
public boolean supprimerDroitsApp(nc.mairie.technique.Transaction aTransaction) throws java.lang.Exception {
	return supprimer(aTransaction);
}
/**
 * Retourne un ArrayList d'objet métier : DroitsApp.
 * @return java.util.ArrayList
 * @param aTransaction aTransaction
 * @throws Exception Exception 
 */
public java.util.ArrayList<DroitsApp> listerDroitsApp(nc.mairie.technique.Transaction aTransaction) throws Exception {
	return executeSelectListe(aTransaction,"select * from "+getTable()+" order by DROIT asc");
}
public java.util.ArrayList<DroitsApp> listerDroitsAppfromGroupe(nc.mairie.technique.Transaction aTransaction, String groupe) throws Exception {
	return executeSelectListe(aTransaction,"select * from "+getTable()+" where CDDRAP IN (SELECT CDDRAP from MAIRIE.SIGRAP where CDGROU ="+groupe+")");
}
public java.util.ArrayList<DroitsApp> listerDroitsAppfromCompteAndApplication(nc.mairie.technique.Transaction aTransaction, String compte, String appli) throws Exception {
	return executeSelectListe(aTransaction,"select * from "+getTable()+" where upper(APPLICATION) = upper('"+appli+"') AND CDDRAP IN (SELECT CDDRAP from MAIRIE.SIGRAP where CDGROU IN (SELECT MAIRIE.SIGRUT.CDGROU from MAIRIE.SIGRUT where upper(CDIDUT) = upper('"+compte+"')))");
}

/**
 * Retourne un ArrayList d'objet métier : DroitsApp.
 * @param aTransaction aTransaction 
 * @return java.util.ArrayList
 * @throws Exception Exception 
 */
public java.util.ArrayList<DroitsApp> listerApplications(nc.mairie.technique.Transaction aTransaction) throws Exception {
	return executeSelectListe(aTransaction,"select distinct APPLICATION from "+getTable()+" order by APPLICATION asc");
}

/**
 * Retourne un DroitsApp.
 * @param aTransaction aTransaction
 * @param cdDrap cdDrap
 * @return DroitsApp DroitsApp
 * @throws Exception Exception
 */
public DroitsApp chercherDroitsApp(nc.mairie.technique.Transaction aTransaction, String cdDrap) throws Exception {
	return (DroitsApp)executeSelect(aTransaction,"select * from "+getTable()+" where CDDRAP = "+cdDrap+"");
}

public int nouvId(nc.mairie.technique.Transaction aTransaction) throws Exception{
//	recherche du dernier 
	return executeCompter(aTransaction, "select max(cddrap) from "+ getTable());
	
}
}
