/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A1;

import A1.B.*;

/**
 *
 * @author Owner
 */
public class UniqueIdentifier {

    static int id;
    static Task E;

    public UniqueIdentifier(Task E) {

        this.id = id;
        this.E = E;
    }

    static int ID() {
        id = System.identityHashCode(E);
        return id;
    }

}