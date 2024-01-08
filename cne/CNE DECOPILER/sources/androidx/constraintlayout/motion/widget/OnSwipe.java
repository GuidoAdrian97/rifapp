package androidx.constraintlayout.motion.widget;

public class OnSwipe {
    private int mAutoCompleteMode = 0;
    private int mDragDirection = 0;
    private float mDragScale = 1.0f;
    private float mDragThreshold = 10.0f;
    private int mFlags = 0;
    private int mLimitBoundsTo = -1;
    private float mMaxAcceleration = 1.2f;
    private float mMaxVelocity = 4.0f;
    private boolean mMoveWhenScrollAtTop = true;
    private int mOnTouchUp = 0;
    private int mRotationCenterId = -1;
    private int mSpringBoundary = 0;
    private float mSpringDamping = Float.NaN;
    private float mSpringMass = 1.0f;
    private float mSpringStiffness = Float.NaN;
    private float mSpringStopThreshold = Float.NaN;
    private int mTouchAnchorId = -1;
    private int mTouchAnchorSide = 0;
    private int mTouchRegionId = -1;

    public OnSwipe setTouchAnchorId(int i) {
        this.mTouchAnchorId = i;
        return this;
    }

    public int getTouchAnchorId() {
        return this.mTouchAnchorId;
    }

    public OnSwipe setTouchAnchorSide(int i) {
        this.mTouchAnchorSide = i;
        return this;
    }

    public int getTouchAnchorSide() {
        return this.mTouchAnchorSide;
    }

    public OnSwipe setDragDirection(int i) {
        this.mDragDirection = i;
        return this;
    }

    public int getDragDirection() {
        return this.mDragDirection;
    }

    public OnSwipe setMaxVelocity(int i) {
        this.mMaxVelocity = (float) i;
        return this;
    }

    public float getMaxVelocity() {
        return this.mMaxVelocity;
    }

    public OnSwipe setMaxAcceleration(int i) {
        this.mMaxAcceleration = (float) i;
        return this;
    }

    public float getMaxAcceleration() {
        return this.mMaxAcceleration;
    }

    public OnSwipe setMoveWhenScrollAtTop(boolean z) {
        this.mMoveWhenScrollAtTop = z;
        return this;
    }

    public boolean getMoveWhenScrollAtTop() {
        return this.mMoveWhenScrollAtTop;
    }

    public OnSwipe setDragScale(int i) {
        this.mDragScale = (float) i;
        return this;
    }

    public float getDragScale() {
        return this.mDragScale;
    }

    public OnSwipe setDragThreshold(int i) {
        this.mDragThreshold = (float) i;
        return this;
    }

    public float getDragThreshold() {
        return this.mDragThreshold;
    }

    public OnSwipe setTouchRegionId(int i) {
        this.mTouchRegionId = i;
        return this;
    }

    public int getTouchRegionId() {
        return this.mTouchRegionId;
    }

    public OnSwipe setOnTouchUp(int i) {
        this.mOnTouchUp = i;
        return this;
    }

    public int getOnTouchUp() {
        return this.mOnTouchUp;
    }

    public OnSwipe setNestedScrollFlags(int i) {
        this.mFlags = i;
        return this;
    }

    public int getNestedScrollFlags() {
        return this.mFlags;
    }

    public OnSwipe setLimitBoundsTo(int i) {
        this.mLimitBoundsTo = i;
        return this;
    }

    public int getLimitBoundsTo() {
        return this.mLimitBoundsTo;
    }

    public OnSwipe setRotateCenter(int i) {
        this.mRotationCenterId = i;
        return this;
    }

    public int getRotationCenterId() {
        return this.mRotationCenterId;
    }

    public float getSpringDamping() {
        return this.mSpringDamping;
    }

    public OnSwipe setSpringDamping(float f) {
        this.mSpringDamping = f;
        return this;
    }

    public float getSpringMass() {
        return this.mSpringMass;
    }

    public OnSwipe setSpringMass(float f) {
        this.mSpringMass = f;
        return this;
    }

    public float getSpringStiffness() {
        return this.mSpringStiffness;
    }

    public OnSwipe setSpringStiffness(float f) {
        this.mSpringStiffness = f;
        return this;
    }

    public float getSpringStopThreshold() {
        return this.mSpringStopThreshold;
    }

    public OnSwipe setSpringStopThreshold(float f) {
        this.mSpringStopThreshold = f;
        return this;
    }

    public int getSpringBoundary() {
        return this.mSpringBoundary;
    }

    public OnSwipe setSpringBoundary(int i) {
        this.mSpringBoundary = i;
        return this;
    }

    public int getAutoCompleteMode() {
        return this.mAutoCompleteMode;
    }

    public void setAutoCompleteMode(int i) {
        this.mAutoCompleteMode = i;
    }
}
