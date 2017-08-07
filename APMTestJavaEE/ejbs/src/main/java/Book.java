import java.io.Serializable;

//mark it entity using Entity annotation
//map table name using Table annoation
@Entity
@Table(name="books")
public class Book implements Serializable {

    private int id;
    private String name;

    public Book(){
    }

    //mark id as primary key with autogenerated value
    //map database column id with id field
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    public int getId() {
        return id;
    }

    @Column(name="name")
    public int getName() {
        return name;
    }
}