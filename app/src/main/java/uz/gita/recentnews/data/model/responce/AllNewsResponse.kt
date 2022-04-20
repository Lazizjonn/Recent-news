package uz.gita.recentnews.data.model.responce

data class AllNewsResponse(
	val total: Int,
	val category: String,
	val articles: List<ArticlesItem>
)