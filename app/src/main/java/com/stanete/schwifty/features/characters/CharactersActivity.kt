package com.stanete.schwifty.features.characters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.pedrogomez.renderers.RVRendererAdapter
import com.pedrogomez.renderers.RendererBuilder
import com.stanete.schwifty.R
import com.stanete.schwifty.core.entity.Characters
import com.stanete.schwifty.core.extension.invisible
import com.stanete.schwifty.core.extension.notRefreshing
import com.stanete.schwifty.core.extension.observe
import com.stanete.schwifty.core.extension.spanSizeLookup
import com.stanete.schwifty.core.extension.visible
import com.stanete.schwifty.core.platform.BaseActivity
import com.stanete.schwifty.core.renderer.Renderable
import kotlinx.android.synthetic.main.activity_characters.progressBar
import kotlinx.android.synthetic.main.activity_characters.recyclerView
import kotlinx.android.synthetic.main.activity_characters.swipeRefreshLayout
import org.koin.android.architecture.ext.viewModel

fun Context.startCharactersActivity() {
  val intent = Intent(this, CharactersActivity::class.java)
  startActivity(intent)
}

class CharactersActivity : BaseActivity() {

  private val viewModel by viewModel<CharactersViewModel>()
  private val adapter: RVRendererAdapter<Renderable> by lazy {
    val rendererBuilder = RendererBuilder<Renderable>()
    rendererBuilder.bind(
        CharacterRenderable::class.java,
        CharacterRenderer()
    )
    rendererBuilder.bind(
        LoadingRenderable::class.java,
        LoadingRenderer(viewModel)
    )
    RVRendererAdapter<Renderable>(rendererBuilder)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_characters)
    configureSwipeRefreshLayout()
    configureRecyclerView()
    observe(viewModel.characters, ::renderCharacters)
    loadCharacters()
  }

  private fun configureSwipeRefreshLayout() {
    swipeRefreshLayout.setOnRefreshListener { reloadCharacters() }
  }

  private fun configureRecyclerView() {
    val layoutManager = GridLayoutManager(this, 2)
    layoutManager.spanSizeLookup {
      adapter.getItem(it)
          .spanSize
    }

    recyclerView.layoutManager = layoutManager
    recyclerView.setHasFixedSize(true)
    recyclerView.adapter = adapter
  }

  private fun reloadCharacters() {
    progressBar.visible()
    adapter.clear()
    viewModel.reloadCharacters()
  }

  private fun loadCharacters() {
    progressBar.visible()
    viewModel.loadCharacters()
  }

  private fun renderCharacters(characters: Characters) {
    swipeRefreshLayout.notRefreshing()
    progressBar.invisible()
    adapter.clear()
    adapter.addAll(characters.results.map {
      CharacterRenderable(it)
    })

    characters.next?.let { adapter.add(LoadingRenderable()) }
    adapter.notifyDataSetChanged()
  }
}
