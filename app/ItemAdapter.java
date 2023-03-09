
public class ItemViewHolder extends RecyclerView.ViewHolder {
    private TextView titleTextView;
    private TextView bodyTextView;

    public ItemViewHolder(View itemView) {
        super(itemView);
        titleTextView = itemView.findViewById(R.id.title_text_view);
        bodyTextView = itemView.findViewById(R.id.body_text_view);
    }

    public void bind(Item item) {
        titleTextView.setText(item.getTitle());
        bodyTextView.setText(item.getBody());
    }
}
