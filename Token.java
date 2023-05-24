/*
 * Vimal Kumawat (vkumaa6a)
 *
 * @author Vimal Kumawat
 * @email vimal.kumawat@airbus.com
 * @version 1.0
 *
 * This project focuses on the implementation of a distributed system, where
 * there are several nodes who communicate among each other via messages. Each
 * node generates a random value and adds its own value while passing the
 * message to the next node.
 */

import java.io.Serializable;

/**
 * Token class creates a token object which is used to send all the relevant
 * information from one node to another. In order to allow token object to be
 * sent from one node to another, the Token class must implement the marker
 * interface - Serializable
 *
 * @author Vimal
 * @variable map contains the updated path which the nodes need to follow
 * @variable total_sum holds the most recent value of the token
 * @variable id id of the node that generated the token
 * @variable idsNo holds the token number that is associated to a particular
 * node id (e.g- node 0 may generate more 2 tokens - token 1 and token 2
 * @variable originalMap contains the original path that is needed to be
 * traversed
 * @variable total_token_for_id has the value which tells how many total number
 * of tokens did the node with a particular id generated. It is used to add the
 * log "All tokens received" in the end, when all the tokens have been received
 * by a particular node
 */
public class Token implements Serializable {

    private String map;
    private int total_sum = 0;
    private int id;
    private int idsNo;
    private String originalMap;
    private int total_token_for_id;

    /**
     * Token constructor initializes the token object with the required
     * information
     *
     * @param identifier is the id of the node
     * @param map is the most updated map which needs to be traversed
     * @param value is stored in the total_sum variable
     * @param idsNo is the token number of node with a particular id
     * @param originalMap is the original path
     * @param total_token_for_id holds the count of total tokens generated by
     * each node
     */
    public Token(int identifier, String map, int value, int idsNo, String originalMap, int total_token_for_id) {
        id = identifier;
        this.map = map;
        total_sum = value;
        this.idsNo = idsNo;
        this.originalMap = originalMap;
        this.total_token_for_id = total_token_for_id;
    }

    /**
     * getTotalTokensForId method returns the total_token_for_id variable
     *
     * @return
     */
    public int getTotalTokensForId() {
        return total_token_for_id;
    }

    /**
     * getMap method return the value stored in map variable
     *
     * @return
     */
    public String getMap() {
        return map;
    }

    /**
     * getOriginalMap returns the value of originalMap variable
     *
     * @return
     */
    public String getOriginalMap() {
        return originalMap;
    }

    /**
     * set_total_sum updates the value of total_sum variable by adding the
     * integer value sent as an argument
     *
     * @param n is the value to be added to the total_sum variable
     */
    public void set_total_sum(int n) {
        total_sum = total_sum + n;
    }

    /**
     * setMap method updates the value of the current map
     *
     * @param newMap
     */
    public void setMap(String newMap) {
        map = newMap;
    }

    /**
     * get_total_sum method returns the value stored in total_sum variable
     *
     * @return
     */
    public int get_total_sum() {
        return total_sum;
    }

    /**
     * get_idsNo method returns the number of the token associated to a
     * particular node id
     *
     * @return
     */
    public int get_idsNo() {
        return idsNo;
    }

    /**
     * getId method returns the id of the node which created this token
     *
     * @return
     */
    public int getId() {
        return id;
    }
}