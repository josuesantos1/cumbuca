defmodule CumbucaTest do
  use ExUnit.Case
  doctest Cumbuca

  test "greets the world" do
    assert Cumbuca.hello() == :world
  end
end
