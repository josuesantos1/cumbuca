defmodule Cumbuca.Application do
  @moduledoc false

  use Application

  @impl true
  def start(_type, _args) do
    children = [
      Plug.Cowboy.child_spec(
        scheme: :http,
        plug: Cumbuca.Endpoint,
        options: [port: 3030]
      )
    ]

    opts = [strategy: :one_for_one, name: Cumbuca.Supervisor]
    Supervisor.start_link(children, opts)
  end
end
