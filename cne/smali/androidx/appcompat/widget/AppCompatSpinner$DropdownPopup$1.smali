.class Landroidx/appcompat/widget/AppCompatSpinner$DropdownPopup$1;
.super Ljava/lang/Object;
.source "AppCompatSpinner.java"

# interfaces
.implements Landroid/widget/AdapterView$OnItemClickListener;


# instance fields
.field final synthetic this$1:Landroidx/appcompat/widget/AppCompatSpinner$DropdownPopup;

.field final synthetic val$this$0:Landroidx/appcompat/widget/AppCompatSpinner;


# direct methods
.method constructor <init>(Landroidx/appcompat/widget/AppCompatSpinner$DropdownPopup;Landroidx/appcompat/widget/AppCompatSpinner;)V
    .locals 0

    .line 975
    iput-object p1, p0, Landroidx/appcompat/widget/AppCompatSpinner$DropdownPopup$1;->this$1:Landroidx/appcompat/widget/AppCompatSpinner$DropdownPopup;

    iput-object p2, p0, Landroidx/appcompat/widget/AppCompatSpinner$DropdownPopup$1;->val$this$0:Landroidx/appcompat/widget/AppCompatSpinner;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onItemClick(Landroid/widget/AdapterView;Landroid/view/View;IJ)V
    .locals 2

    .line 978
    iget-object p1, p0, Landroidx/appcompat/widget/AppCompatSpinner$DropdownPopup$1;->this$1:Landroidx/appcompat/widget/AppCompatSpinner$DropdownPopup;

    iget-object p1, p1, Landroidx/appcompat/widget/AppCompatSpinner$DropdownPopup;->this$0:Landroidx/appcompat/widget/AppCompatSpinner;

    invoke-virtual {p1, p3}, Landroid/widget/AdapterView;->setSelection(I)V

    .line 979
    iget-object p1, p0, Landroidx/appcompat/widget/AppCompatSpinner$DropdownPopup$1;->this$1:Landroidx/appcompat/widget/AppCompatSpinner$DropdownPopup;

    iget-object p1, p1, Landroidx/appcompat/widget/AppCompatSpinner$DropdownPopup;->this$0:Landroidx/appcompat/widget/AppCompatSpinner;

    invoke-virtual {p1}, Landroid/widget/AdapterView;->getOnItemClickListener()Landroid/widget/AdapterView$OnItemClickListener;

    move-result-object p1

    if-eqz p1, :cond_0

    .line 980
    iget-object p1, p0, Landroidx/appcompat/widget/AppCompatSpinner$DropdownPopup$1;->this$1:Landroidx/appcompat/widget/AppCompatSpinner$DropdownPopup;

    iget-object p4, p1, Landroidx/appcompat/widget/AppCompatSpinner$DropdownPopup;->this$0:Landroidx/appcompat/widget/AppCompatSpinner;

    iget-object p1, p1, Landroidx/appcompat/widget/AppCompatSpinner$DropdownPopup;->mAdapter:Landroid/widget/ListAdapter;

    .line 981
    invoke-interface {p1, p3}, Landroid/widget/Adapter;->getItemId(I)J

    move-result-wide v0

    invoke-virtual {p4, p2, p3, v0, v1}, Landroid/widget/AdapterView;->performItemClick(Landroid/view/View;IJ)Z

    .line 983
    :cond_0
    iget-object p1, p0, Landroidx/appcompat/widget/AppCompatSpinner$DropdownPopup$1;->this$1:Landroidx/appcompat/widget/AppCompatSpinner$DropdownPopup;

    invoke-virtual {p1}, Landroidx/appcompat/widget/ListPopupWindow;->dismiss()V

    return-void
.end method
