package ru.nivanov.model;

/**
 * Created by Nikolay Ivanov on 03.10.2017.
 */
public class Vacancy {

    private final String name;
    private final String url;
    private final String author;
    private final String authorUrl;
    private final long createDate;

    /**
     * Constructor.
     * @param name ..
     * @param url ..
     * @param author ..
     * @param createDate ..
     */
    public Vacancy(String name, String url, String author, String authorUrl, long createDate) {
        this.name = name;
        this.url = url;
        this.author = author;
        this.authorUrl = authorUrl;
        this.createDate = createDate;
    }

    /**
     * Creation date getter.
     * @return ..
     */
    public long getCreateDate() {
        return createDate;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vacancy vacancy = (Vacancy) o;

        return getName().equals(vacancy.getName()) && getUrl().equals(vacancy.getUrl()) && getAuthor().equals(
                vacancy.getAuthor()) && getAuthorUrl().equals(vacancy.getAuthorUrl());
    }

    /**
     * Name getter.
     * @return ..
     */
    public String getName() {
        return name;
    }

    /**
     * Url getter.
     * @return ..
     */
    public String getUrl() {
        return url;
    }

    /**
     * Author getter.
     * @return ..
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Author url getter.
     * @return ..
     */
    public String getAuthorUrl() {
        return authorUrl;
    }

    @Override
    public int hashCode() {

        final int constant = 31;
        int result = getName().hashCode();
        result = constant * result + getUrl().hashCode();
        result = constant * result + getAuthor().hashCode();
        result = constant * result + getAuthorUrl().hashCode();
        return result;
    }
}
