document.addEventListener("DOMContentLoaded", function () {
  const chatIcon = document.getElementById("chatbotIcon");
  const chatWindow = document.getElementById("chatWindow");
  const sendBtn = document.getElementById("sendBtn");
  const chatInput = document.getElementById("chatInput");
  const chatMessages = document.getElementById("chatMessages");
  const closeChat = document.getElementById("closeChat");

  // Open/close chat window
  chatIcon.addEventListener("click", () => {
    chatWindow.style.display =
      chatWindow.style.display === "flex" ? "none" : "flex";
  });

  closeChat.addEventListener("click", () => {
    chatWindow.style.display = "none";
  });

  // Send message
  sendBtn.addEventListener("click", sendMessage);
  chatInput.addEventListener("keypress", function (e) {
    if (e.key === "Enter") sendMessage();
  });

  async function sendMessage() {
    const message = chatInput.value.trim();
    if (!message) return;

    // Show user message
    chatMessages.innerHTML += `<div><strong>You:</strong> ${message}</div>`;
    chatInput.value = "";

    // Send to backend
    const response = await fetch("/chatbot/ask", {
      method: "POST",
      headers: { "Content-Type": "text/plain" },
      body: message,
    });

    const reply = await response.text();
    chatMessages.innerHTML += `<div><strong>Bot:</strong> ${reply}</div>`;
    chatMessages.scrollTop = chatMessages.scrollHeight;
  }
});
