package com.loc.seekmax.domain.usecases.jobs

data class JobsUseCases(
    val getJobs: GetJobs,
    val searchJobs: SearchJobs,
    val upsertArticle: UpsertArticle,
    val deleteArticle: DeleteArticle,
    val getArticles: GetArticles,
    val getArticle: GetArticle
)