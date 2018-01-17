package Ch_2_5_Applications;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Practise_2_5_16 {
    static class Name implements Comparable<Name> {
        private final String name;
        public Name(String s) { this.name = s.toUpperCase(); }
        private static String order = "RWQOJMVAHBSGZXNTCIEKUPDYFL";
        public int compareTo(Name v) {
            int n = Math.min(name.length(), v.name.length());
            for (int i = 0; i < n; i++) {
                int aindex = order.indexOf(name.charAt(i));
                int bindex = order.indexOf(v.name.charAt(i));
                if (aindex != bindex) return aindex - bindex;
            }
            return name.length() - v.name.length();
        }
        public static Name[] names(String[] s) {
            Name[] n = new Name[s.length];
            for (int i = 0; i < s.length; i++)
                n[i] = new Name(s[i]);
            return n;
        }
        public String toString() { return name; } 
    }
    public static String randomName() {
        int len = StdRandom.uniform(4, 10);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++)
            sb.append((char)('a' + StdRandom.uniform(0, 26)));
        return sb.toString();
    }
    public static void main(String[] args) {
        String[] s = new String[200];
        for (int i = 0; i < s.length; i++)
            s[i] = randomName();
        Name[] a = Name.names(s);
        Arrays.sort(a);
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
    }
    // output
    /*
     *  RWWGWXSCZ
        ROSZL
        ROIFINLWD
        RUQPYZJFI
        WBKXCH
        WTKYRQ
        WPZY
        QQOE
        QJQJ
        QAHC
        QSAHY
        QGHPLETA
        QXOSYD
        QXID
        QNIUSPW
        QFNFV
        OWPVI
        OQMDTTXE
        OJFWQ
        OASEMKQSC
        OZBDQ
        OXERUIU
        OECEQ
        OPYCLRTV
        OPLIOT
        JMWFSF
        JBZC
        JSAUUAUV
        JSCMMC
        JZAVRX
        JXOC
        JXFSXNF
        JNFJZE
        JTWHHV
        JTTUZWG
        JEYP
        JPTGU
        JPPYEBVWY
        JYDLKO
        MQAI
        MZNA
        MZDD
        MITDSJGRQ
        MUMOK
        MDXBR
        MYRYAV
        MYZMT
        VOGRAWM
        VXIXI
        VNDXGE
        VTVDVJPZM
        VKGJGLRQ
        VPGP
        VPLSFX
        VDOSWTA
        VDMMWZ
        AOPMJZG
        ASWQQIVH
        AGIDDALZ
        AXXZJZ
        AEHUSL
        AEFEJIKR
        AKVQFFKE
        AUKLA
        AUPUQBKA
        APICCNMX
        ADMTY
        HRCKUNMPI
        HWZU
        HORFJJAOI
        HMTJFSNM
        HAHHKBJE
        BRQM
        BRPU
        BWZWNQ
        BQPAAN
        BMBYBZEFY
        BSKRD
        BGYV
        BXXRIXNJ
        BEZKZCPGN
        BEYEUKSY
        BDKLRLB
        SQGDCMPMO
        SBWDGRN
        SXZS
        SNDVIOTZ
        SCMCKI
        GWWKWJRAS
        GHRY
        GGTIPD
        GTOWVWZEU
        ZWYAPZY
        ZQJJ
        ZOTWKVGM
        ZZFUL
        ZTOYBD
        ZEGH
        ZUJQTJRAS
        ZDTYBH
        XZCEIBEI
        XNWHK
        XNDBF
        XCSRX
        XEWYK
        XKKPCXYY
        XPKGQEN
        NOVXPWG
        NHKHXTN
        NGKL
        NCWR
        NECREEN
        NKJH
        NYQELNBRI
        NFOCNNOY
        TMBXYFB
        TVXDV
        TNJHIIM
        TESQNCVT
        TEPUPNWWR
        TPXOGGXH
        TDGET
        TDDZJZB
        CWRNLOS
        CQERCTQKG
        CONPG
        CJYEZJYZP
        CAFYZVBHG
        CGNOBCMX
        CIZRXUEU
        CIKDC
        CPZIUB
        CFMPEYC
        IWTY
        IWUBK
        IQJN
        IQKOQJWN
        IIXLCG
        IPUPRQ
        IDNZUVA
        EQWZZ
        EMRJRO
        EHCXBZV
        EBNEZF
        EBCH
        ESWBHKUA
        EGNHWE
        EXAHBZ
        EKUS
        EUONCB
        EFPASRO
        KWISKKDQX
        KOZQ
        KMESRVYY
        KHJSENZCV
        KZFUDNO
        KTOIJOVGT
        KYGW
        KYFRW
        URZX
        UOJTXNW
        UNKZF
        UTXO
        UCWLEUE
        UCUNKSB
        UECQMXXHO
        PQYSVNCES
        POWYH
        PBWBIFJQ
        PBOU
        PENIBZ
        PKSAIYKI
        DQJWPPTW
        DASCKWZ
        DHHACBE
        DTVKFQC
        DCETPUD
        DISYDF
        DPVLGVUH
        YWHXOL
        YMRWTUX
        YGTXZXMZ
        YCCLLIVV
        YDLH
        YLGNLCBS
        FRRVSI
        FRFGUMFU
        FOGTOSNB
        FODGGAUPR
        FJPF
        FVLKVQ
        FAVACSGR
        FXKPFJJFU
        FNSKWQGL
        FKISPFE
        FUQWHRSXT
        LVNYMSGR
        LAJGGBTF
        LNMP
        LPWZPIX

     */
}
