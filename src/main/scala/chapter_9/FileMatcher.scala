package chapter_9

import java.io.File

/**
  * Created by denys on 4/11/17.
  */
object FileMatcher {
  private def filesHere = (new File(".")).listFiles()

  def filesEnding(query: String) = {
    for (file <- filesHere; if (file.getName.endsWith(query))) yield file
  }

  def filesContaining(query: String) = {
    for (file <- filesHere; if (file.getName.contains(query))) yield file
  }

  def filesRegex(query: String) = {
    for (file <- filesHere; if (file.getName.matches(query))) yield file
  }

  def filesMatching(query: String, matcher: (String, String) => Boolean) = {
    for (file <- filesHere; if matcher(file.getName, query)) yield file
  }

  def filesMatching(matcher: String => Boolean) = {
    for (file <- filesHere; if matcher(file.getName)) yield file
  }

  def filesEndingWithPlaceholder(query: String) = {
    filesMatching(_.endsWith(query))
  }

  def filesContainingWithPlaceholder(query: String) = {
    filesMatching(_.contains(query))
  }

  def filesRegexWithPlaceholder(query: String) = {
    filesMatching(_.matches(query))
  }
}
