package main.CapaPresentacio;

import com.formdev.flatlaf.FlatIntelliJLaf;
import main.CapaDomini.Controllers.CtrlDomini;
import main.CapaDomini.Models.Cela;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.XYChart;
import java.io.File;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
/**
 * Ctrl Presentacio es la classe encarregada de comunicar la Capa de Presentacio amb la capa del domini.
 * @author Marc Castells
 * @author Gerard Castell
 * @author Gerard Alvarez
 */
public class CtrlPresentacio {

    /**
     * Instancia del controlador de domini
     */
    private final CtrlDomini Cd;
    /**
     * Instancia de la Vista Principal
     */
    private final VistaPrincipal vc;


    //CREADORA

    /**
     * Crea una instancia del ctrl Presentacio
     * @throws Exception En cas d'error treu exepcio
     */
    public CtrlPresentacio() throws Exception {
        Cd = new CtrlDomini();
        FlatIntelliJLaf.setup();
        vc = new VistaPrincipal("Excel", this);
        vc.setVisible(true);
    }
    //DOCUMENTS

    /**
     * Funcio que crida ctrl domini per crea un document
     * @param doc  nom que li volem possar al document
     */
    public void crearDoc(String doc){
        Cd.CrearDocument(doc);
    }

    /**
     * Funcio que crida ctrl domini per canviar el nom d'un document
     * @param doc nou nom del doc
     */
    public void NouNomDoc(String doc){
        Cd.CanviarNomDoc(doc);
    }

    /**
     * Funcio que crida ctrl domini per agafar els fulls de un document
     * @return array amb el nombre de fulls
     */
    public ArrayList<String> GetFulls(){
        return Cd.GetFullDoc();
    }
    //FULLS

    /**
     * Funcio que crida ctrl domini per agafar totes les celes de un full
     * @param full full del que volem agafar celes
     * @return "matriu" strings amb els contingut de les celes
     */
    public String[][] MostrarLlista(String full) {
        return Cd.Mostrar(full);
    }

    /**
     * Funcio que crida ctrl domini per agafar el nombre de files
     * @param full full del que volem les files
     * @return nombre files
     */
    public int GetFiles(String full) {
        return Cd.getNum_Files(full);
    }

    /**
     * Funcio que crida ctrl domini per agafar el nombre de columnes
     * @param full full del que volem les cols
     * @return nombre columnes
     */
    public int GetColumnes(String full) {
        return Cd.getNum_Columnes(full);
    }

    /**
     * Funcio que crida ctrl domini per creat un nou full
     * @param full nom del nou full
     * @param nf nombre files
     * @param nc nombre columnes
     */
    public void CrearNouFull(String full, Integer nf, Integer nc){
        Cd.CrearFull(full,nf,nc);
    }

    /**
     * Funcio que crida ctrl domini per eliminar un full
     * @param elimFull nom del full a eliminar
     */
    public void EliminarFull(String elimFull){
        Cd.eliminarFull(elimFull);
    }

    /**
     * Funcio que crida ctrl domini per canviar nom full
     * @param antic nom full actual
     * @param nou nou nom del full
     */
    public void NouNomFull(String antic, String nou){
        Cd.CanviarNomFull(antic, nou);
    }
    //CELA

    /**
     * Funcio que crida ctrl domini per modificar el contingut de una cela
     * @param full full on esta la cela
     * @param id identificador de la cela
     * @param contingut nou contingut de la cela
     * @throws Exception Exepcio en cas d'error
     */
    public void ModificarContingutCela(String full, AbstractMap.SimpleEntry<Integer, Integer> id, String contingut) throws Exception {
        Cd.modificarContingutCela(full, id, contingut);
    }

    /**
     * Funcio que crida ctrl domini per agafar el tipus de cela
     * @param full full on esta la cela
     * @param id identificador de la cela
     * @return el tipus de la cela
     */
    public String GetTipusCela(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.GetTipusCela(full, id);
    }

    /**
     * Funcio que crida ctrl domini per retornar el tipus i subtipus de una cela
     * @param full full on esta la cela
     * @param id identificador de la cela
     * @return tipus i subtipus de la cela
     */
    public String GetTipusCelaComplete(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.GetTipusCelaComplete(full, id);
    }

    /**
     * Funcio que crida ctrl domini per retornar el subtipus numero
     * @param full full on esta la cela
     * @param id identificador de la cela
     * @return el subtipus de numero
     */
    public String GetTipusNumero(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.GetTipusNumero(full, id);
    }

    /**
     * Funcio que crida ctrl domini per canviar el subtipus de un numero
     * @param full full on esta la cela
     * @param id identificador de la cela
     * @param tipus nou tipus
     */
    public void CanviarTipusNumero(String full, AbstractMap.SimpleEntry<Integer, Integer> id, String tipus) {
        Cd.CanviarTipusNumero(full, id, tipus);
    }

    /**
     * Funcio que crida ctrl domini per calcular el increment de un Numero
     * @param full full on esta la cela
     * @param id identificador de la cela
     * @return un bool segons l'estat
     * @throws Exception Exepcio en cas d'error
     */
    public int CalculaIncrement(String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws Exception {
        return Cd.CalculaIncrement(full, id);
    }

    /**
     * Funcio que crida ctrl domini per calcular increment i reemplasar una cela
     * @param full full on esta la cela
     * @param id identificador de la cela
     * @param id_Remp id de la cela a reemplasar
     * @return numero segons l'estat de la operació
     * @throws Exception Exepcio en cas d'error
     */
    public int CalculaIncrementIReemplaca(String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> id_Remp) throws Exception {
        return Cd.CalculaIncrementIReemplaca(full, id, id_Remp);
    }

    /**
     * Funcio que crida ctrl domini per calcula la reduccio de una cela
     * @param full full on esta la cela
     * @param id identificador de la cela
     * @return numero segons lestat
     * @throws Exception Exepcio segons l'estat
     */
    public int CalculaReduir(String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws Exception {
        return Cd.CalculaReduir(full, id);
    }

    /**
     * Funcio que crida ctrl domini per calcular la reduccio i reemplasar una cela
     * @param full full on esta la cela
     * @param id identificador de la cela
     * @param idRemp id de la cela a reemplasar
     * @return numero segons l'estat
     * @throws Exception Exepcio en cas d'error
     */
    public int CalculaReduirIReemplaca(String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        return Cd.CalculaReduirIReemplaca(full, id, idRemp);
    }

    /**
     * Funcio que crida ctrl domini per calcular una potencia
     * @param full full on esta la cela
     * @param id identificador de la cela
     * @param exp exponent potencia
     * @return nombre segons l'estat
     * @throws Exception Exepcio en cas d'error
     */
    public int CalculaPotencia(String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp) throws Exception {
        return Cd.CalculaPotencia(full, id, exp);
    }

    /**
     * Funcio que crida ctrl domini per calcular potencia i reemplasar cela
     * @param full full on esta la cela
     * @param id identificador de la cela
     * @param exp exponent potencia
     * @param idRemp id de la cela a reemplasar
     * @return nombre segons l'estat
     * @throws Exception Exepcio en cas d'error
     */
    public int CalculaPotenciaIReemplaca(String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        return Cd.CalculaPotenciaIReemplaca(full, id, exp, idRemp);
    }

    /**
     *  Funcio que crida ctrl domini per calcular arrel
     * @param full full on esta la cela
     * @param id identificador de la cela
     * @param exp radical arrel
     * @return nombre segons l'estat
     * @throws Exception Exepcio en cas d'error
     */
    public int CalculaArrel(String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp) throws Exception {
        return Cd.CalculaArrel(full, id, exp);
    }

    /**
     * Funcio que crida ctrl domini per calcular arrel i reemplasar
     * @param full full on esta la cela
     * @param id identificador de la cela
     * @param exp radical de l'arrel
     * @param idRemp id de la cela a reemplasar
     * @return nombre segons l'estat
     * @throws Exception Expecio en cas d'error
     */
    public int CalculaArrelIReemplaca(String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        return Cd.CalculaArrelIReemplaca(full, id, exp, idRemp);
    }

    /**
     * Funcio que crida ctrl domini per calcular valor absolut
     * @param full full on esta la cela
     * @param id identificador de la cela
     * @return nombre segons l'estat
     * @throws Exception Exepcio en cas d'error
     */
    public int CalculaValorAbs(String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws Exception {
        return Cd.CalculaValorAbs(full, id);
    }

    /**
     * Funcio que crida ctrl domini per Calcular valor absolut i reemplasar
     * @param full full on esta la cela
     * @param id identificador de la cela
     * @param idRemp id de la cela a remplasar
     * @return nombre segons l'estat
     * @throws Exception Exepcio en cas d'error
     */
    public int CalculaValorAbsIReemplaca(String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        return Cd.CalculaValorAbsIReemplaca(full, id, idRemp);
    }

    /**
     * Funcio que crida ctrl domini per Calcular conversio
     * @param full full on esta la cela
     * @param id identificador de la cela
     * @param c tipus de la conversio
     * @throws Exception Expecio en cas d'error
     */
    public void CalculaConversio(String full, AbstractMap.SimpleEntry<Integer, Integer> id, String c) throws Exception {
        Cd.CalculaConversio(full, id, c);
    }

    /**
     * Funcio que crida ctrl domini per calcular conversio i reemplasar
     * @param full full on esta la cela
     * @param id identificador de la cela
     * @param c tipus de la conversio
     * @param idRemp id cela a remplasar
     * @throws Exception Exepcio en cas d'error
     */
    public void CalculaConversioIReemplaca(String full, AbstractMap.SimpleEntry<Integer, Integer> id, String c, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        Cd.CalculaConversioIReemplaca(full, id, c, idRemp);
    }

    /**
     * Funcio que crida ctrl domini per canviar nombre decimals
     * @param full full on esta la cela
     * @param id identificador de la cela
     * @param dec nombre decimals nou
     */
    public void CanviarDecimals(String full, AbstractMap.SimpleEntry<Integer, Integer> id, Integer dec){
        Cd.CanviarDecimals(full, id, dec);
    }

    /**
     * Funcio que crida ctrl domini per canviar arrodoniment
     * @param full full on esta la cela
     * @param id identificador de la cela
     * @param arrodonir tipus arrodoniment
     */
    public void CanviarArrodonit(String full, AbstractMap.SimpleEntry<Integer, Integer> id, Boolean arrodonir) {
        Cd.CanviarArrodonit(full, id, arrodonir);
    }

    /**
     * Funcio que crida ctrl domini per posar cela a tot majuscules
     * @param full full on esta la cela
     * @param id identificador de la cela
     */
    public void AllMayus(String full, AbstractMap.SimpleEntry<Integer, Integer> id){
        Cd.AllMayus(full, id);
    }

    /**
     * Funcio que crida ctrl domini per posar cela tot minuscules
     * @param full full on esta la cela
     * @param id identificador de la cela
     */
    public void AllMinus(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Cd.AllMinus(full, id);
    }

    /**
     * Funcio que crida ctrl domini per retornar el dia d'una data
     * @param full full on esta la cela
     * @param id identificador de la cela
     * @return dia de la data
     */
    public String getDia(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.getDia(full, id);
    }

    /**
     * Funcio que crida ctrl domini per retornar mes de una data
     * @param full full on esta la cela
     * @param id identificador de la cela
     * @return mes de una data
     */
    public String getMes(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.getMes(full, id);
    }

    /**
     * Funcio que crida ctrl domini per retornar dia setmanal de una data
     * @param full full on esta la cela
     * @param id identificador de la cela
     * @return dia setmanal de una data
     */
    public String getWeekday(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.getWeekday(full, id);
    }

    /**
     * Funcio que crida ctrl domini per retornar any de una data
     * @param full full on esta la cela
     * @param id identificador de la cela
     * @return any d'una data
     */
    public String getAny(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.getAny(full, id);
    }

    /**
     * Funcio que crida ctrl domini per retornar data completa
     * @param full full on esta la cela
     * @param id identificador de la cela
     * @return data completa amb dia setmanal inclos
     */
    public String getDataCompleta(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.getDataCompleta(full, id);
    }

    /**
     * Funcio que crida ctrl domini per canviar le format de la data a text
     * @param full full on esta la cela
     * @param id identificador de la cela
     * @return boolean segons l'estat

     */
    public boolean transformaText(String full, AbstractMap.SimpleEntry<Integer, Integer> id){
        return Cd.transformaText(full, id);
    }

    /**
     * Funcio que crida ctrl domini per transformar data a format data
     * @param full full on esta la cela
     * @param id identificador de la cela
     * @return boolean segons l'estat
     */
    public boolean transformaData(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.transformaData(full, id);
    }

    /**
     * Funcio que crida ctrl domini per comprovar si una cela es buida
     * @param full full on esta la cela
     * @param id identificador de la cela
     * @return true si esta buida, si no falç
     */
    public boolean ComprovaCelaNoOcupa(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.ComprovaCelaNoOcupa(full, id);
    }

    /**
     * Funcio que crida ctrl domini per copiar un bloc de celes a un altre lloc
     * @param full full on volem trobar les celes
     * @param id1 cela inici del bloc a copiar
     * @param id2 cela inici del bloc on colocarem
     * @param idfin1 cela final del bloc a copiar
     * @param idfin2 cela final del bloc on colocarem
     * @throws Exception en cas d'error
     */
    public void Copiar(String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2, AbstractMap.SimpleEntry<Integer, Integer> idfin1, AbstractMap.SimpleEntry<Integer, Integer> idfin2) throws Exception {
        Cd.copiar(full, id1, id2, idfin1, idfin2);
    }

    /**
     * Funcio que crida ctrl domini per comprova si existeix una id
     * @param full on volem trobar la cela
     * @param id identificador cela
     * @return si existeix true, si no fals
     */
    public boolean ComprovarId(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.ComprovarId(full, id);
    }

    /**
     * Funcio que crida ctrl domini per afegir fila
     * @param full on volem colocar la fila
     * @param f numero de fila on la volem colocar
     * @throws Exception En cas de error, Exepcio
     */
    public void AfegirFila(String full, Integer f) throws Exception {
        Cd.AfegirFila(full, f);
    }

    /**
     *Funcio que crida ctrl domini per afegir col
     * @param full on volem colocar la columna
     * @param c numero de columna on la volem colocar
     * @throws Exception En cas de error, Exepcio
     */
    public void AfegirCol(String full, Integer c) throws Exception {
        Cd.AfegirCol(full, c);
    }

    /**
     * Funcio que crida ctrl domini per eliminar fila
     * @param full on volem colocar la fila
     * @param fi numero de fila on la volem treure
     * @throws Exception En cas de error, Exepcio
     */
    public void EliminarFila(String full, Integer fi) throws Exception {
        Cd.EliminarFila(full, fi);
    }

    /**
     * Funcio que crida ctrl domini per elimianr col
     * @param full on volem colocar la columna
     * @param co numero de columna on la volem treure
     * @throws Exception En cas de error, Exepcio
     */
    public void EliminarCol(String full, Integer co) throws Exception {
        Cd.EliminarCol(full, co);
    }

    /**
     * Funcio que crida ctrl domini que retorna el contingut de una Cela. Si es referencia contingut, si no resultat final
     * @param full on volem trobar la cela
     * @param id identificador cela
     * @return valor total de la cela
     */
    public String ValorTotal(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.ValorTotal(full, id);
    }

    /**
     * Funcio que crida ctrl domini per retornar el resultat final
     * @param full on volem trobar la cela
     * @param id identificador cela
     * @return resultat final cela
     */
    public String resultatfinal(String full,AbstractMap.SimpleEntry<Integer, Integer> id){
        return Cd.resultatfinal(full,id);
    }

    /**
     * Funcio que crida ctrl domini que guarda els canvis de un document
     * @param fileName nom del Doc
     * @param path path on es vol guardar el document
     * @throws Exception Exepcio en cas d'Error
     */
    public void guardarDocument(String fileName, File path) throws Exception {
        Cd.guardarDocument(fileName, path);
    }

    /**
     * Funcio que crida ctrl domini que ens permet obrir un document d'un path en concret
     * @param fileName nom del doc
     * @param path path on es troba el document a obrir
     * @throws Exception Exepcio en cas d'error
     */
    public void obrirDocument(String fileName, File path) throws Exception {
        Cd.obrirDocument(fileName, path);
    }

    /**
     * Funcio que crida ctrl domini per fer graf linear
     * @param full full on es troben le celes
     * @param Col1 nombre de la primera col
     * @param filI1 de la primera col la fila inicial
     * @param filF1 de la primera col la fila final
     * @param Col2 segona columna
     * @param filI2 de la segona col la fila inicial
     * @param filF2 de la segona col la fila final
     * @return retorna un graf linearn
     */
    public XYChart LinearChart(String full, Integer Col1, Integer filI1, Integer filF1,Integer Col2, Integer filI2, Integer filF2) throws Exception {
        return Cd.LinearChart(full, Col1, filI1, filF1, Col2, filI2, filF2);
    }

    /**
     * Funcio que crida ctrl domini per fer graf circular
     * @param full full on es troben le celes
     * @param Col1 nombre de la primera col
     * @param filI1 de la primera col la fila inicial
     * @param filF1 de la primera col la fila final
     * @param Col2 segona columna
     * @param filI2 de la segona col la fila inicial
     * @param filF2 de la segona col la fila final
     * @return retorna un graf circular
     */
    public PieChart PieChart(String full, Integer Col1, Integer filI1, Integer filF1, Integer Col2, Integer filI2, Integer filF2) throws Exception {
        return Cd.PieChart(full, Col1, filI1, filF1, Col2, filI2, filF2);
    }

    /**
     * Funcio que crida ctrl domini per fer graf barres
     * @param full full on es troben le celes
     * @param Col1 nombre de la primera col
     * @param filI1 de la primera col la fila inicial
     * @param filF1 de la primera col la fila final
     * @param Col2 segona columna
     * @param filI2 de la segona col la fila inicial
     * @param filF2 de la segona col la fila final
     * @return retorna un graf de barres
     */
    public CategoryChart Histograma(String full, Integer Col1, Integer filI1, Integer filF1, Integer Col2, Integer filI2, Integer filF2) throws Exception {
        return Cd.Histograma(full, Col1, filI1, filF1, Col2, filI2, filF2);
    }

    /**
     * Funcio que permet importar un fitxer en csv d'un path
     * @param fileName nom del fitxer a importar
     * @param path lloc on es troba el fitxer
     * @throws Exception Exepcio en cas d'error
     */
    public void ImportarCSV(String fileName, File path) throws Exception {
        Cd.ImportarCSV(fileName, path);
    }

    /**
     * Funcio que permet exportar un full a un fitxer CSV
     * @param fileName nom amb el qual es guarda el fitxer
     * @param path lloc on guardarem el fitxer
     * @param full nom del full on es guardara
     * @throws Exception Exepcio en cas d'error
     */
    public void exportarCSV(String fileName, File path, String full) throws Exception {
        Cd.exportarCSV(fileName, path, full);
    }

    /**
     * Funcio que crida el ctrl domini per operar sobre un bloc
     * @param full full on estan
     * @param id1 ids inicial bloc 1
     * @param id2 id inicial bloc 2
     * @param idfin1 id final bloc 1
     * @param idfin2 id final bloc 2
     * @param operacio operacio que volem realitzar
     * @param oper operands
     * @return retorna el resultat de la operacio numerica
     * @throws Exception Exepcio en cas d'erro
     */
    public int Opera_bloc (String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2, AbstractMap.SimpleEntry<Integer, Integer> idfin1, AbstractMap.SimpleEntry<Integer, Integer> idfin2, String operacio, Double oper) throws Exception {
        return Cd.Operar_bloc(full, id1, id2, idfin1, idfin2, operacio, oper);
    }

    /**
     * Funcio que crida ctrl domini per ordena un bloc
     * @param full full on estan celes
     * @param id1 id inical
     * @param id2 id final
     * @param cols array amb nombre columnes
     * @param cont a contar
     * @throws Exception Exepcio en cas d'Error
     */
    public void ordena_bloc(String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer >id2, ArrayList<Integer> cols, String cont) throws Exception {
    Cd.ordena_bloc(full, id1, id2, cols, cont);
    }

    /**
     * Funcio que crida al ctrl domini per buscar un cert string a les celes
     * @param full full al qual busquem la particula
     * @param busc paraula o caracters a buscar
     * @return array amb celes que contenen el string
     */
    public ArrayList<Cela> Busca(String full, String busc){
        return Cd.Busca(full, busc);
    }

    /**
     * Funcio que crida al ctrl domini per buscar un string i reemplaçar-lo per un altre
     * @param full full al qual busquem la particula
     * @param buscar paraula o caracters a buscar
     * @param remp en cas de trobar els caracters es reemplacen per aquest string
     * @return un array amb les celes que si compleixen la condicio
     */
    public ArrayList<Cela> BuscarRemp(String full, String buscar, String remp) throws Exception {
        return Cd.BuscaRemp(full, buscar, remp);
    }

    /**
     * Funcio que crida ctrl domini que conta el numero de paraules de una cela
     * @param full full on es troba la cela
     * @param id identificador de la cela al full
     * @return el nombre de paraules que conte
     */
    public String countWords(String full, AbstractMap.SimpleEntry<Integer, Integer> id){
        return Cd.countWords(full, id);
    }

    /**
     * Funcio que crida ctrl domini que conta el numero de caracters d'una cela
     * @param full full on es troba la cela
     * @param id identificador de la cela al full
     * @return el nombre de caracters que conte
     */
    public String countChars(String full, AbstractMap.SimpleEntry<Integer, Integer> id){
        return Cd.countChars(full, id);
    }

    /**
     * Funcio que crida ctrl domini que conta el numero de vocals d'una cela
     * @param full full on es troba la cela
     * @param id identificador de la cela al full
     * @return el nombre de vocals que conte
     */
    public String countVowels(String full, AbstractMap.SimpleEntry<Integer, Integer> id){
        return Cd.countVowels(full, id);
    }

    /**
     * Funcio que crida ctrl domini per guardar els canvis en un doc ja guardat previament
     * @return retorna un int per indicar els estats a altres capes
     * @throws Exception Exepcio en cas d'Error
     */
    public int guardarDoc() throws Exception {
        return Cd.guardarDoc();
    }

    /**
     * Funcio que crida ctrl domini que comprova si un doc en path ja ha estat guardat
     * @param fileName nom del document
     * @param path path on podria ser guardat
     * @return true si ja existeix, si no fals
     * @throws IOException Exepcio en cas d'error
     */
    public Boolean ComprovaDocExisteix(String fileName, File path) throws IOException {
        return Cd.ComprovaDocExisteix(fileName, path);
    }

    public String get_data_doc(){
        return Cd.getdataDoc();
    }
    public String get_data_mod_doc(){
        return Cd.getdatamodDoc();
    }

    public int get_num_fulls(){
        return Cd.get_num_fulls();
    }
}
