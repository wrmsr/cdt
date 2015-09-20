package lpg.lpgjavaruntime;

public interface IAstVisitor
{
    boolean preVisit(IAst element);
    void postVisit(IAst element);
}
