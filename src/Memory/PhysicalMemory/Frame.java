package Memory.PhysicalMemory;

import Memory.VirtualMemory.Page;

public class Frame {
    private Page page;
    private boolean containsPage;

    public Frame(){
        page = null;
        containsPage = false;
    }
    public Frame(Page page){
        this.page = page;
        containsPage = true;
    }

    public Page getPage() {
        return page;
    }

    public void clear(){
        page = null;
        containsPage = false;
        System.out.println("clear");
    }

    public void setPage(Page page) {
        if(page != null){
            this.page = page;
            containsPage = true;
        }
        else{
            clear();
        }
    }

    public boolean containsPage() {
        return containsPage;
    }

    @Override
    public String toString() {
        return "|"+
                (!containsPage ? " " : page)
                +"|";
    }
}
