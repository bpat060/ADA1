/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A1;

/**
 *
 * @author Owner
 */
public class UniqueIdentifier {

    //singletons pattern counter
    static int id;
    static Task task;

    public UniqueIdentifier(Task task) {

        this.id = id;
        this.task = task;
    }

    static int ID() {
        id = System.identityHashCode(task);
        return id;
    }

}
