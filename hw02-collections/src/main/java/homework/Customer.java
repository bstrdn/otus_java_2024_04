package homework;

public class Customer {
    private final long id;
    private final String[] name;
    private long scores;

    // todo: 1. в этом классе надо исправить ошибки

    public Customer(long id, String name, long scores) {
        this.id = id;
        this.name = new String[]{name};
        this.scores = scores;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name[0];
    }

    public void setName(String name) {
        this.name[0] = name;
    }

    public long getScores() {
        return scores;
    }

    public void setScores(long scores) {
//        this.scores = scores;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name='" + name + '\'' + ", scores=" + scores + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;
        return id == customer.id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
}
