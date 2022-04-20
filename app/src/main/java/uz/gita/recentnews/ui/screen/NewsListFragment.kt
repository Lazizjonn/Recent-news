package uz.gita.recentnews.ui.screen

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.recentnews.R
import uz.gita.recentnews.databinding.FragmentNewsListBinding
import uz.gita.recentnews.presentation.NewsViewModel
import uz.gita.recentnews.presentation.impl.NewsViewModelImpl
import uz.gita.recentnews.ui.adapter.NewsListAdapter

@AndroidEntryPoint
class NewsListFragment : Fragment(R.layout.fragment_news_list) {
    private val binding by viewBinding(FragmentNewsListBinding::bind)
    private val viewModel: NewsViewModel by viewModels<NewsViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = NewsListAdapter()

        binding.listNews.layoutManager = LinearLayoutManager(requireContext())
        binding.listNews.adapter = adapter

//        viewModel.allNews("all")
        viewModel.noNetConnectionLivedata.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Network error", Toast.LENGTH_SHORT).show()
        }

        viewModel.loadNewsLivedata.observe(viewLifecycleOwner) { v ->
            v.articles.let {
                Log.d("TAG", "onViewCreated: $it")
                adapter.submitList(it)
            }
        }
    }
}